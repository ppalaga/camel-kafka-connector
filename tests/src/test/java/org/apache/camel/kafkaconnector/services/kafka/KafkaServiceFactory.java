/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.camel.kafkaconnector.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KafkaServiceFactory {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaServiceFactory.class);

    private KafkaServiceFactory() {
    }

    public static KafkaService createService() {
        String kafkaRemote = System.getProperty("kafka.instance.type");
        if (kafkaRemote == null || kafkaRemote.equals("local-kafka-container")) {
            return new ContainerLocalKafkaService();
        }

        if (kafkaRemote.equals("remote")) {
            return new RemoteKafkaService();
        }

        LOG.error("Invalid Kafka instance type: {}. Must be one of 'local-kafka-container' or 'remote",
                kafkaRemote);
        throw new UnsupportedOperationException("Invalid Kafka instance type:");
    }
}
