angular.module('myApp.factoryModule', [])

.factory('factoryAnagrafica', function() {
  var anagrafica = {
    ragioneSociale: null,
    partitaIva: null,
    codiceRea: null,
    indirizzo: null,
    civico: null,
    cap: null,
    citta: null,
    provincia: null,
    stato: null
  };

  return {
    setAnagrafica: function(mappaDati) {
      anagrafica.ragioneSociale = mappaDati.ragioneSociale;
      anagrafica.partitaIva = mappaDati.partitaIva;
      anagrafica.codiceRea = mappaDati.codiceRea;
      anagrafica.indirizzo = mappaDati.indirizzo;
      anagrafica.civico = mappaDati.civico;
      anagrafica.cap = mappaDati.cap;
      anagrafica.citta = mappaDati.citta;
      anagrafica.provincia = mappaDati.provincia;
      anagrafica.stato = mappaDati.stato;
    },
    getAnagrafica: function(){
      var valid = true;
      for(var key in anagrafica){
        if(anagrafica[key] == null) {
          valid = false;
          break;
        }
      }
      return valid ? anagrafica : null;
    }
  };
})

.factory('factoryDipendente', function(){
  var dipendente = {
    nome: null,
    cognome: null,
    codiceFiscale: null,
    costoOrario: null,
    oreLavorative: null,
    inizioRapporto: null,
    fineRapporto: null,
    oreLavorative: null,
    oreFormazione: null
  };

  return {
    setDipendente: function(mappaDati) {
      dipendente.nome = mappaDati.nome;
      dipendente.cognome = mappaDati.cognome;
      dipendente.codiceFiscale = mappaDati.codiceFiscale;
      dipendente.costoOrario = mappaDati.costoOrario;
      dipendente.oreLavorative = mappaDati.oreLavorative;
      dipendente.inizioRapporto = mappaDati.inizioRapporto;
      dipendente.fineRapporto = mappaDati.fineRapporto;
      dipendente.oreLavorative = mappaDati.oreLavorative;
      dipendente.oreFormazione = mappaDati.oreFormazione;
    },
    getDipendente: function(){
      var valid = true;
      for(var key in dipendente){
        if(dipendente[key] == null) {
          valid = false;
          break;
        }
      }
      return valid ? dipendente : null;
    }
  };
})

.factory('factoryCorso', function(){
  var corso = {
    idCorso: null,
    titolo: null,
    descrizione: null,
    durata: null,
    aula: null,
    dateCorso: null,
    materiale: null,
    listaPartecipanti: null,
    listaDocenti: null,
    modalitaFormative: null
  };

  return {
    setCorso: function(mappaDati) {
      corso.idCorso = mappaDati.idCorso;
      corso.titolo = mappaDati.titolo;
      corso.descrizione = mappaDati.descrizione;
      corso.durata = mappaDati.durata;
      corso.aula = mappaDati.aula;
      corso.dateCorso = mappaDati.dateCorso;
      corso.materiale = mappaDati.materiale;
      corso.listaPartecipanti = mappaDati.listaPartecipanti;
      corso.listaDocenti = mappaDati.listaDocenti;
      corso.modalitaFormative = mappaDati.modalitaFormative;
    },
    getCorso: function(){
      var valid = true;
      for(var key in corso){
        if(corso[key] == null) {
          valid = false;
          break;
        }
      }
      return valid ? corso : null;
    }
  };
})

.factory('factoryAula', function() {
  var aula = {
    descrizione: null,
    numero: null,
    postiMax: null,
    numeroCivico: null,
    cap: null,
    piano: null,
    note: null
  };

  return {
    setAula: function(mappaDati) {
      aula.descrizione = mappaDati.descrizione;
      aula.numero = mappaDati.numero;
      aula.postiMax = mappaDati.postiMax;
      aula.numeroCivico = mappaDati.numeroCivico;
      aula.cap = mappaDati.cap;
      aula.piano = mappaDati.piano;
      aula.note = mappaDati.note;
    },
    getAula: function(){
      var valid = true;
      for(var key in aula){
        if(aula[key] == null) {
          if(key == "descrizione" || key == "note") continue;
          valid = false;
          break;
        }
      }
      return valid ? aula : null;
    }
  };
})
