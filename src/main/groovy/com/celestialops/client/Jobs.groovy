package com.celestialops.client

import wslite.rest.ContentType

@Mixin(Serviceable) 
class Jobs {

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

 def runningJobs() {
   get(path:'jobs/running').json
 }

 def doneJob(tid) {
   get(path:"jobs/done/${tid}").json
 }

 def status(jid, queue) {
   get(path:"jobs/${queue}/${jid}/status").json
 }
 
 def waitFor(tid, timeout) {
    int count = (timeout/1000)
    while (count > 0) { 
       def job = doneJob(tid)
       if(job!=[:]) {
          if(job.status=='success') {
            return true          
	    } else if(job.status=='error') {
		return false
	    }
	 }
       sleep(1000) 
       count -- 
    } 
   throw new RuntimeException("Failed to wait for job with transaction id ${tid} to finish")
 }
}
