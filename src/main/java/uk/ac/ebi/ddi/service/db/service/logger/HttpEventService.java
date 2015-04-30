package uk.ac.ebi.ddi.service.db.service.logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import uk.ac.ebi.ddi.service.db.exception.DBWriteException;
import uk.ac.ebi.ddi.service.db.model.logger.HttpEvent;
import uk.ac.ebi.ddi.service.db.repo.logger.IHttpEventRepo;

import java.math.BigInteger;

/**
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 29/04/2015
 */
public class HttpEventService implements IHttpEventService {

    @Autowired
    private IHttpEventRepo accessRepo;

    @Override
    public HttpEvent save(HttpEvent httpEvent) {
        if((httpEvent.getAbstractResource() != null && httpEvent.getAbstractResource().getId() == null))
            new DBWriteException(" The reference to the original resource should contain an Id");

        return accessRepo.save(httpEvent);
    }

    @Override
    public HttpEvent read(BigInteger id) {
        return accessRepo.findOne(id);
    }

    @Override
    public Page<HttpEvent> readAll(int pageStart, int size) {
        return accessRepo.findAll(new PageRequest(pageStart, size));
    }

    @Override
    public HttpEvent update(HttpEvent httpEvent) {

        HttpEvent existingHttpEvent = accessRepo.findOne(httpEvent.getId());

        existingHttpEvent.setAccessDate(httpEvent.getAccessDate());
        existingHttpEvent.setHost(httpEvent.getHost());
        existingHttpEvent.setPath(httpEvent.getPath());
        existingHttpEvent.setReferrer(httpEvent.getReferrer());
        existingHttpEvent.setRequest(httpEvent.getRequest());
        existingHttpEvent.setResponse_size(httpEvent.getResponse_size());
        existingHttpEvent.setStatus(httpEvent.getStatus());
        existingHttpEvent.setUser(httpEvent.getUser());
        existingHttpEvent.setUserAgent(httpEvent.getUserAgent());
        existingHttpEvent.setLogName(httpEvent.getLogName());
        existingHttpEvent.setRawMessage(httpEvent.getRawMessage());
        existingHttpEvent.setAbstractResource(httpEvent.getAbstractResource());

        return save(existingHttpEvent);
    }

    @Override
    public HttpEvent delete(BigInteger id) {
        accessRepo.delete(id);
        return accessRepo.findOne(id);
    }
}
