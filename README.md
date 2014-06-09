# Intro

A Groovy based Celestial client

# Usage

Using with gradle add:

```groovy
repositories {
  maven { url 'http://jcenter.bintray.com' }
}


dependencies {
  compile 'com.celestialops:groovy-celestial:0.0.2'
}
```

Configure client by adding:

```groovy
// resources/com/celestialops/client/Config.groovy
celestial {
  user = 'admin'
  password = 'changeme'
  url = 'https://localhost:8443/'
}
```

Using flows is covers high level operations:

```groovy
 import com.celestialops.client.*

 def flows = new Flows()
 // system json and staging max wait time
 def id = flows.stageFlow(json, 5000)
 // destroying a system
 flows.destroyFlow(id, 5000)
```

Low level API:

```groovy
 // basicly what flows use
 def id = system.create(json).id 
 def jid = job.stage(id).job
 def running = job.runningJobs().jobs.find{ it.jid == jid } 
 assert job.waitUntil(running.tid, Jobs.State.Succesful, timeout) == true
```

# Copyright and license

Copyright [2013] [Ronen Narkis]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
