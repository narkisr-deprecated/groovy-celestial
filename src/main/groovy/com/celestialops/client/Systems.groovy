package com.celestialops.client;
 
import wslite.http.HTTPClientException
import wslite.rest.ContentType
import groovy.json.*

@Mixin(Serviceable) 
class Systems {

 def create(system) {
   try{
     def res = post(path:'systems'){
      type ContentType.JSON 
      json system
     }
     res.json
   } catch (HTTPClientException e) {
     if(e.response!=null){
       throw new RuntimeException(new String(e.response.data))
     } else {
     	 throw e
     }
   }
 }

 def delete(id) {
   delete(path:"systems/${id}")
 }

 def get(id) {
   get(path:"systems/${id}").json
 }

 def query(start, offset, query) {
   def encoded = new JsonBuilder(query).toString().bytes.encodeBase64().toString()
   get(path:'systems/query', query: ['page':start, 'offset':offset, 'query':encoded]).json
 }

}
