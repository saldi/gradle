/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.performance.regression.corefeature

import org.gradle.initialization.StartParameterBuildOptions
import org.gradle.internal.os.OperatingSystem
import org.gradle.performance.AbstractCrossVersionPerformanceTest

class AbstractIncrementalExecutionPerformanceTest extends AbstractCrossVersionPerformanceTest {

    def setup() {
        runner.useToolingApi = true
        if (OperatingSystem.current().windows) {
            // Reduce the number of iterations on Windows, since the performance tests take 3 times as long.
            runner.warmUpRuns = 5
            runner.runs = 20
        } else {
            runner.warmUpRuns = 10
            runner.runs = 40
        }
    }


    protected boolean enableConfigurationCaching(boolean configurationCachingEnabled) {
        runner.args.add("-D${StartParameterBuildOptions.ConfigurationCacheOption.PROPERTY_NAME}=${configurationCachingEnabled}")
    }
}
