package com.kaos.musa.services;

import com.kaos.musa.entities.Lead;
import com.kaos.musa.entities.Reader;
import com.kaos.musa.repositories.LeadRepository;
import com.kaos.musa.repositories.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {

    @Autowired
    private LeadRepository leadRep;

    @Autowired
    private ReaderRepository readerRep;

    public void insertReader(Reader reader){
        readerRep.save(reader);
    }

    public void insertLead(Lead lead) { leadRep.save(lead); }

    public boolean isEmailAlreadyRegistered(Reader reader) {
        return readerRep.existsByEmail(reader.getEmail());
    }

    public boolean isEmailAlreadyRegistered(Lead lead) {
        return leadRep.existsByEmail(lead.getEmail());
    }


}
