package com.celestialops.client

class Jobs {

 def stage(id) {
   Celestial.getInstance().post(path:"jobs/stage/${id}"){}
 }

 def jobs() {
   Celestial.getInstance().get(path:'jobs')
 }

 def waitUntil(jid, expected, timeout) {
   	
 }
}
