function onlyLetters(input){
  return /^[a-zA-Z]+$/.test(input);
}

function onlyLettersAndSpaces(input){
  return /^[a-zA-Z][a-zA-Z\s]*$/.test(input);
}

function onlyDigits(input){
  return /^\d+$/.test(input);
}

function validMailAddress(input){
  const regex = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9](?:[A-Z0-9-]{0,62}[A-Z0-9])?\.){1,8}[A-Z]{2,63}$/;
  return input.length < 254 && regex.test(input.toUpperCase());
}

function validIban(input){
  const regex = /^([A-Z]{2}[ \-]?[0-9]{2})(?=(?:[ \-]?[A-Z0-9]){9,30}$)((?:[ \-]?[A-Z0-9]{3,5}){2,7})([ \-]?[A-Z0-9]{1,3})?$/;
  return regex.test(input.toUpperCase());
}

function validCF(input){
  const regex = /^[A-Z]{6}[0-9]{2}[ABCDEHLMPRST][0-9]{2}([A-Z][0-9]{3})[A-Z]$/;
  return input.length == 16 && regex.test(input.toUpperCase());
}

function dateDelay(first, second){
  const date1 = new Date(first);
  const date2 = new Date(second);
  const millis_in_a_day = 1000 * 60 * 60 * 24;
  const result = Math.floor((date2.getTime() - date1.getTime()) / millis_in_a_day);
  return result > 0;
}

function dateNotInFuture(input){
  const today = new Date();
  return dateDelay(input, today);
}

function atLeast18yo(dataNascita){
  if(!dateNotInFuture) return false;
  const today = new Date();
  const birth = new Date(dataNascita);
  const yearsDelay = today.getFullYear() - birth.getFullYear();
  if(yearsDelay > 18) return true;
  if(yearsDelay < 18) return false;
  birth.setFullYear(today.getFullYear());
  const delay = today.getTime() - birth.getTime();
  return delay > 0;
}



function checkIndirizzo(input){
  return onlyLettersAndSpaces(input);
}

function checkNumeroCivico(input){
  return onlyDigits(input);
}

function checkCap(input){
  return input.length == 5 && onlyDigits(input);
}

function checkNome(input){
  return onlyLettersAndSpaces(input);
}

function checkCognome(input){
  return onlyLettersAndSpaces(input);
}

function checkDataDiNascita(input){
  return atLeast18yo(input);
}

function checkEmail(input){
  return validMailAddress(input);
}

function checkPec(input){
  return validMailAddress(input);
}

function checkNumeroTelefonico(input){
  return onlyDigits(input); //TODO devo gestire i prefissi (e il +)? Devo gestire la lunghezza (differente tra fisso e mobile)?
}

function checkInizioFineAttivita(dataInizio, dataFine){
  return (dataFine ? (dateNotInFuture(dataInizio) && dateDelay(dataInizio, dataFine)) : dateNotInFuture(dataInizio) )
}

function checkCodiceFiscale(input){
  return validCF(input);
}

function checkPartitaIva(input){
  return input.length == 11 && onlyDigits(input);
}

function checkNumeroIscrizioneCCIAA(input){
  //TODO non ho idea di come sia fatto
}

function checkDataIscrizioneCCIAA(input){
  return dateNotInFuture(input);
}

function checkIban(input){
  return validIban(input);
}

function getBase64Image(img) {
  var canvas = document.createElement("canvas");
  canvas.width = img.width;
  canvas.height = img.height;

  var ctx = canvas.getContext("2d");
  ctx.drawImage(img, 0, 0);

  var dataURL = canvas.toDataURL("image/png");

  return dataURL.replace(/^data:image\/(png|jpg);base64,/, "");
}

function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
}
