package model.builder;

import model.Client;

public class ClientBuilder {
    private Long id;
    private String name;
    private Long id_card_number;
    private Long pnc;
    private String address;
    private String email;

    public ClientBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setId_card_number(Long id_card_number) {
        this.id_card_number = id_card_number;
        return this;
    }

    public ClientBuilder setPnc(Long pnc) {
        this.pnc = pnc;
        return this;
    }

    public ClientBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public Client createClient() {
        return new Client(id, name, id_card_number, pnc, address, email);
    }
}