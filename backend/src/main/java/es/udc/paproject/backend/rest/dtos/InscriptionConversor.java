package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Inscription;

import java.util.List;
import java.util.stream.Collectors;

public class InscriptionConversor {
    private InscriptionConversor() {}

    public final static InscriptionDto toInscriptionDto(Inscription inscription) {
        return new InscriptionDto(inscription.getId(),inscription.getCreditCard().length()>3?inscription.getCreditCard().substring(inscription.getCreditCard().length()-4):inscription.getCreditCard(), inscription.getEvent().getId(), inscription.getEvent().getSportEventName(),inscription.getDorsal(), inscription.isPicked(), inscription.getValoration(), inscription.getInscriptionDate(), inscription.getEvent().getDate(), inscription.getEvent().hasStarted(), inscription.isRated());
    }

    public final static List<InscriptionDto> toInscriptionDtos(List<Inscription> inscriptions) {
        return inscriptions.stream().map(c -> toInscriptionDto(c)).collect(Collectors.toList());
    }

}
