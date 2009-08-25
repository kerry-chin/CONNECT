package gov.hhs.fha.nhinc.nhindocretrieve.proxy;

import gov.hhs.fha.nhinc.common.nhinccommonproxy.RespondingGatewayCrossGatewayRetrieveRequestType;
import ihe.iti.xds_b._2007.RetrieveDocumentSetResponseType;

/**
 *
 *
 * @author Neil Webb
 */
public class NhinDocRetrieveNoOpImpl implements NhinDocRetrieveProxy
{

    public RetrieveDocumentSetResponseType respondingGatewayCrossGatewayRetrieve(RespondingGatewayCrossGatewayRetrieveRequestType request)
    {
        return new RetrieveDocumentSetResponseType();
    }
    
}
