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
   post(path:"jobs/stage/${id}",{}).json
 }

 def destroy(id) {
   post(path:"jobs/destroy/${id}",{}).json
 }

 def launch(action, id, args) {
    post(path:"jobs/${action}/${id}",{
      type ContentType.JSON 
      json args	
    }).json
 }

 def listJobs() {
   get(path:'jobs').json
 }

 def status(jid, queue) {
   get(path:"jobs/${queue}/${jid}/status").json
 }
 
 def waitUntil(tid, expected, timeout) {
    int count = (timeout/1000)
    while (count > 0) { 
       if(containsTid(listJobs(), tid, expected)){
        return true
       }
       sleep(1000) 
       count -- 
    } 
   throw new RuntimeException("Failed to wait for job with transaction id ${tid} to finish ${expected.result}")
 }
}
