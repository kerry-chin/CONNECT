/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gov.hhs.fha.nhinc.patientdiscovery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hl7.v3.PRPAIN201306UV02;
import gov.hhs.fha.nhinc.nhinclib.NullChecker;
import gov.hhs.fha.nhinc.saml.extraction.SamlTokenExtractor;
import gov.hhs.fha.nhinc.saml.extraction.SamlTokenExtractorHelper;
import javax.xml.ws.WebServiceContext;
import org.hl7.v3.ProxyPRPAIN201305UVProxyRequestType;
import gov.hhs.fha.nhinc.nhincproxypatientdiscovery.NhincProxyPatientDiscovery;
import gov.hhs.fha.nhinc.nhincproxypatientdiscovery.NhincProxyPatientDiscoveryPortType;


/**
 *
 * @author dunnek
 */
public class NhinPatientDiscoveryImpl {
    private static Log log = LogFactory.getLog(NhinPatientDiscoveryImpl.class);
    private static final String SERVICE_NAME = "mockpatientdiscovery";

    public org.hl7.v3.PRPAIN201306UV02 proxyPRPAIN201305UV(org.hl7.v3.PRPAIN201305UV02 body, WebServiceContext context) {
        PRPAIN201306UV02 response =null;
        ProxyPRPAIN201305UVProxyRequestType request = new ProxyPRPAIN201305UVProxyRequestType();

        request.setPRPAIN201305UV02(body);
        request.setAssertion(SamlTokenExtractor.GetAssertion(context));

        String homeCommunityId = SamlTokenExtractorHelper.getHomeCommunityId();
        if (NullChecker.isNotNullish(homeCommunityId)) {
            NhincProxyPatientDiscovery service = new NhincProxyPatientDiscovery();
            NhincProxyPatientDiscoveryPortType port = service.getNhincProxyPatientDiscoveryPort();
            ((javax.xml.ws.BindingProvider) port).getRequestContext().put(javax.xml.ws.BindingProvider.ENDPOINT_ADDRESS_PROPERTY, SamlTokenExtractorHelper.getEndpointURL(homeCommunityId, SERVICE_NAME));

            response = port.proxyPRPAIN201305UV(request);
        } else {
           response = null;
        }
        return response;
    }
}
