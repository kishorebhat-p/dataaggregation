package com.bofa.kafka.dataaggregation.streamdata;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.state.KeyValueStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bofa.kafka.dataaggregation.doa.PaymentAggregatedInfo;
import com.bofa.kafka.dataaggregation.doa.PaymentInfo;
import com.bofa.kafka.dataaggregation.doa.RemittanceInfo;
import com.bofa.kafka.dataaggregation.doa.TransferInfo;
import com.bofa.kafka.dataaggregation.serdes.CustomSerdes;

@Service
public class KafkaStreamDataService {

	private final Logger logger = LoggerFactory.getLogger(KafkaStreamDataService.class);

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Value(value = "${spring.kafka.consumer.group-id}")
	private String userGroupId;

	@Value(value = "${kafka.topic.payment}")
	private String paymentTopic;

	@Value(value = "${kafka.topic.transfer}")
	private String transferTopic;

	@Value(value = "${kafka.topic.remittance}")
	private String remittanceTopic;

	// kafka.topic.aggregated=aggregatedInfo
	@Value(value = "${kafka.topic.aggregated}")
	private String aggregatedInfoTopic;

	@Autowired
	AggregatedValueMapper mapper;
	@Autowired
	PaymentTransactionJoiner joiner;
	@Autowired
	PaymentRemittanceJoiner joiner2;

	@PostConstruct
	public void startStreamingService() {

		try {
			final Properties streamsConfiguration = new Properties();
			streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, userGroupId);
			streamsConfiguration.put(StreamsConfig.CLIENT_ID_CONFIG, userGroupId);
			streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
			// Set the default key serde
			streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
			// Set the default value serde
			streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, PaymentAggregatedInfo.class);
			streamsConfiguration.put(StreamsConfig.APPLICATION_SERVER_CONFIG, bootstrapServers);
			final File example = Files.createTempDirectory("TestParentChildTopic_3").toFile();
			streamsConfiguration.put(StreamsConfig.STATE_DIR_CONFIG, example.getPath());
			KafkaStreams streams = createStreams(streamsConfiguration);
			streams.cleanUp();
			streams.start();

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				try {
					streams.close();
				} catch (final Exception e) {
					// ignored
				}
			}));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	KafkaStreams createStreams(final Properties streamsConfiguration) {

		final Serde<String> stringSerde = Serdes.String();
		final StreamsBuilder builder = new StreamsBuilder();

		final KTable<String, PaymentInfo> paymentInfo = builder.table(paymentTopic,
				Consumed.with(stringSerde, CustomSerdes.paymentSerde()));

		final KTable<String, TransferInfo> transactionDetails = builder.table(transferTopic,
				Consumed.with(stringSerde, CustomSerdes.transferSerde()));

		final KTable<String, RemittanceInfo> remiitanceDetails = builder.table(remittanceTopic,
				Consumed.with(stringSerde, CustomSerdes.remittanceSerde()));

		Materialized.as("MaterializedDataForStream").withRetention(Duration.ofMinutes(3));
		Materialized<String, PaymentAggregatedInfo, KeyValueStore<Bytes, byte[]>> with = Materialized.with(stringSerde,
				CustomSerdes.paymentAggrSerde());

		final KTable<String, PaymentAggregatedInfo> fullPaymentDetails = paymentInfo.join(transactionDetails, joiner)
				.join(remiitanceDetails, joiner2, with);

		final KTable<String, PaymentAggregatedInfo> fullPaymentDetails2 = fullPaymentDetails.mapValues(mapper);

		fullPaymentDetails2.toStream().to(aggregatedInfoTopic);

		return new KafkaStreams(builder.build(), streamsConfiguration);

	}

}
