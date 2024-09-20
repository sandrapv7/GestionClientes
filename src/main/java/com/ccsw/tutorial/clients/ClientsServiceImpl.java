package com.ccsw.tutorial.clients;

import com.ccsw.tutorial.clients.model.Clients;
import com.ccsw.tutorial.clients.model.ClientsDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    ClientsRepository clientsRepository;

    //Responde con una Entity
    @Override
    public List<Clients> findAll() {
        return (List<Clients>) this.clientsRepository.findAll();
    }

    @Override
    public void save(Long id, ClientsDto dto) throws Exception {
        if (this.clientsRepository.findByName(dto.getName()).isPresent()) {
            throw new Exception("Ja existeix un usuari amb aquest nom");
        }
        Clients client;
        if (id == null) {
            client = new Clients();

        } else {
            client = this.clientsRepository.findById(id).orElse(null);
        }

        client.setName(dto.getName());
        this.clientsRepository.save(client);
    }

    @Override
    public void delete(Long id) throws Exception {

        if (this.clientsRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not Exists");
        }
        this.clientsRepository.deleteById(id);
    }
}
