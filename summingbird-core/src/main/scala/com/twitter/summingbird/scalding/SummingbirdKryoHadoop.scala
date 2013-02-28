/*
Copyright 2013 Twitter, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.twitter.summingbird.scalding

import com.esotericsoftware.kryo.Kryo
import com.twitter.scalding.serialization.KryoHadoop
import com.twitter.summingbird.util.KryoRegistrationHelper
import com.twitter.summingbird.scalding.ConfigBijection.fromJavaMap

/**
 * @author Oscar Boykin
 * @author Sam Ritchie
 * @author Ashu Singhal
 */

class SummingbirdKryoHadoop extends KryoHadoop {
  import KryoRegistrationHelper._

  override def decorateKryo(newK: Kryo) {
    super.decorateKryo(newK)
    val m = fromJavaMap.invert(getConf)
    registerBijections(newK, m)
    registerBijectionDefaults(newK, m)
    registerKryoClasses(newK, m)
  }
}
