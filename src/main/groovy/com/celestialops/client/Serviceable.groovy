package com.celestialops.client

import wslite.rest.*
import wslite.http.auth.*

class Serviceable {

  @Delegate RESTClient client = new RESTClient()

  def Serviceable(){
   client.with {
     url = 'https://localhost:8443/'
     httpClient.sslTrustAllCerts = true
     authorization = new HTTPBasicAuthorization("admin", "changeme")
     defaultAcceptHeader = 'application/json'
   }
  }
}
