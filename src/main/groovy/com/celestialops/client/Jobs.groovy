package com.celestialops.client

@Mixin(Serviceable) 
class Jobs {

 def containsTid = { jobs,tid,exp -> jobs."${exp.result}".any{it.tid == tid} }

 enum State {
  Succesful('succesful'),
  Erroneous('erroneous')
  def result 
  def State(result){
   this.result = result
  }
 }
 
 def stage(id) {
   post(path:"jobs/stage/${id}"){}
 }

 def listJobs() {
   get(path:'jobs')
 }

 def status(jid, queue) {
   get(path:"jobs/${queue}/${jid}/status")
 }
 
 def waitUntil(tid, expected, timeout) {
    int count = (timeout/1000)
    while (count > 0) { 
       if(containsTid(listJobs().json, tid, expected)){
        return true
       }
       sleep(1000) 
       count -- 
    } 
   throw new RuntimeException("Failed to wait for job with transaction id ${tid} to finish ${expected.result}")
 }
}
