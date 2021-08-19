package au.gov.ato.wsdl;

import java.math.BigInteger;
import java.net.URL;
import java.util.Iterator;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.junit.Test;

import au.gov.asic.wsdl.nni.search.external.v3.ExternalSearchNniName;
import au.gov.asic.wsdl.nni.search.external.v3.ExternalSearchNniNameServiceV3;
import uri.business_document_header_types_asic_gov.BusinessDocumentHeaderType;
import uri.nni_types_asic_gov.NameIndexAcncEntityType;
import uri.nni_types_asic_gov.SearchNniRequestType;
import uri.v3_external_search_nni_name_asic_gov.SearchNniNameReplyType;
import uri.v3_external_search_nni_name_asic_gov.SearchNniNameRequestType;

// @Author: Johnathan Ingram (johnathan.ingram@ato.gov.au)
public class LiveService {

   @Test
   public void testLiveConnection() {
      // https://download.asic.gov.au/media/4999931/message-implementation-guide-for-infobrokers-v15.pdf
      String endpointUrl = "https://www.gateway.uat.asic.gov.au/gateway/ExternalSearchNniNamePortV3";
      String username = "abrs2asic@ato.gov.au";
      String password = "T0d@y1234";
      ExternalSearchNniNameServiceV3 service = new ExternalSearchNniNameServiceV3();


      // Available ports
      Iterator<QName> it = service.getPorts();
      while (it.hasNext()) {
         QName m = it.next();
         System.out.println("Port QName: " + m);
      }

      ExternalSearchNniName web = service.getPort(ExternalSearchNniName.class);
      ((BindingProvider)web).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
      ((BindingProvider) web).getRequestContext().put(BindingProvider.USERNAME_PROPERTY, username);
      ((BindingProvider) web).getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, password);
      ((BindingProvider) web).getRequestContext().put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);

      SearchNniNameRequestType search = new SearchNniNameRequestType();
      
      BusinessDocumentHeaderType headerType = new BusinessDocumentHeaderType();
      headerType.setSenderId("nniName@somewhere.com");
      //headerType.setSenderType("REGA");
      headerType.setSenderType("INFO");
      headerType.setMessageType("searchNniName");
      headerType.setMessageVersion(3);
      headerType.setMessageReferenceNumber(UUID.randomUUID().toString());      
      search.setBusinessDocumentHeader(headerType);

      SearchNniRequestType.Organisation org = new SearchNniRequestType.Organisation();
      org.setName("apple");
      // type & status optional

      SearchNniRequestType requestType = new SearchNniRequestType();
      requestType.setSearchType("S");
      requestType.setSearchScope("A");
      requestType.setOrganisation(org);
      requestType.setMaxResult(new BigInteger("10"));
      search.setBusinessDocumentBody(requestType);

      SearchNniNameReplyType response = web.externalSearchNniName(search);
      System.out.println("Reference: " + response.getBusinessDocumentHeader().getMessageReferenceNumber());
      for (NameIndexAcncEntityType sNam : response.getBusinessDocumentBody().getOrganisation()) {
         System.out.println("Jurisdiction:" + sNam.getJurisdiction());
         System.out.println("Inc @:" + sNam.getPlaceOfIncorporation());
         System.out.println("Name:" + sNam.getName().getName());
      }
   }
}
