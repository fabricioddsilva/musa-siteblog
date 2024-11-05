package com.kaos.musa.services;

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

    //Reader Section
    public void insertReader(Reader reader){
        readerRep.save(reader);
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return readerRep.existsByEmail(email);
    }
}
