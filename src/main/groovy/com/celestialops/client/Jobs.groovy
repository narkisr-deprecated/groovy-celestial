package com.celestialops.client

class Jobs {

 def containsJid = { jobs,jid,exp -> jobs."${exp.result}".any{it.jid == jid} }

 enum State {
  Succesful('succesful'),
  Erroneous('erroneous')
  def result 
  def State(result){
   this.result = result
  }
 }
 
 def stage(id) {
   Celestial.getInstance().post(path:"jobs/stage/${id}"){}
 }

 def jobs() {
   Celestial.getInstance().get(path:'jobs')
 }

 
 def waitUntil(jid, expected, timeout) {
    int count = (timeout/1000)
    while (count > 0) { 
       if(containsJid(jobs().json, jid, expected)){
        return true
       }
       sleep(1000) 
       count -- 
    } 
   throw new RuntimeException("Failed to wait for job with id ${jid} to finish ${expected.result}")
 }
}
