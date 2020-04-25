angular.module('myApp.excelModule', [])

.factory('factoryExcel', function() {

  const X = XLSX;

  var process_wb = function(wb, format) {
  	function to_json(workbook) {
  		var result = {};
  		workbook.SheetNames.forEach(function(sheetName) {
  			var roa = X.utils.sheet_to_json(workbook.Sheets[sheetName], {header:1});
  			if(roa.length) result[sheetName] = roa;
  		});
  		return JSON.stringify(result, 2, 2);
  	};

  	function to_csv(workbook) {
  		var result = [];
  		workbook.SheetNames.forEach(function(sheetName) {
  			var csv = X.utils.sheet_to_csv(workbook.Sheets[sheetName]);
  			if(csv.length){
  				result.push("SHEET: " + sheetName);
  				result.push("");
  				result.push(csv);
  			}
  		});
  		return result.join("\n");
  	};

  	function to_fmla(workbook) {
  		var result = [];
  		workbook.SheetNames.forEach(function(sheetName) {
  			var formulae = X.utils.get_formulae(workbook.Sheets[sheetName]);
  			if(formulae.length){
  				result.push("SHEET: " + sheetName);
  				result.push("");
  				result.push(formulae.join("\n"));
  			}
  		});
  		return result.join("\n");
  	};

		var output = "";
		switch(format) {
			case "form": output = to_fmla(wb); break;
			case "json": output = to_json(wb); break;
			default: output = to_csv(wb);
		}
    return output;
  };

  var export_wb = function(purpose, data, filename, format, sheetnames){

    var write_to_file = function(filename, blob){
      let dir = cordova.file.dataDirectory;
      window.requestFileSystem(LocalFileSystem.PERSISTENT, 0, function(fileSystem) {

        alert('file system open: ' + fileSystem.name);
        fileSystem.root.getDirectory("Exceldir", {create: true}, function(dirEntry){

          dirEntry.getFile(filename, {create: true, exclusive: false}, function(fileEntry){

            alert("NAME: " + fileEntry.name + "\nPATH: " + fileEntry.fullPath);

            fileEntry.createWriter(function(fileWriter) {

              fileWriter.onwriteend = function(e) {
                // for real-world usage, you might consider passing a success callback
                alert('Write of file "' + filename + '" completed in ' + fileEntry.fullPath +'.');
              }

              fileWriter.onerror = function(e) {
                // you could hook this up with our global error handler, or pass in an error callback
                alert('Write failed: ' + e.toString());
              }
              fileWriter.write(blob);
            });
          });
        });
      });
      window.resolveLocalFileSystemURL(dir, function(dirEntry){
        dirEntry.getFile(filename, {create: true, exclusive: false}, function(fileEntry){

          alert("USING CORDOVA.FILE.DATADIRECTORY\nNAME: " + fileEntry.name + "\nPATH: " + fileEntry.fullPath);

          fileEntry.createWriter(function(fileWriter) {

            fileWriter.onwriteend = function(e) {
              // for real-world usage, you might consider passing a success callback
              alert('Write of file "' + filename + '" completed in ' + fileEntry.fullPath +'.');
            }

            fileWriter.onerror = function(e) {
              // you could hook this up with our global error handler, or pass in an error callback
              alert('Write failed: ' + e.toString());
            }
            fileWriter.write(blob);
          });
        });
      });
    };

    function readFile(fileEntry) {
      fileEntry.file(function (file) {
          var reader = new FileReader();

          reader.onloadend = function() {
              console.log("Successful file read: " + this.result);
              displayFileData(fileEntry.fullPath + ": " + this.result);
          };

          reader.readAsText(file);

      }, onErrorReadFile);
    }

    function s2ab(s) {
      var buf = new ArrayBuffer(s.length);
      var view = new Uint8Array(buf);
      for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
      return buf;
    }

    function build_riepilogoTotale(){
      const header = [{
        A: "ID CORSO",
        B: "ENTE ATTUATORE",
        C: "TITOLO AZIONE",
        D: "DATE AULA",
        E: "ORARIO AULA",
        F: "SEDE CORSO",
        G: "PARTECIPANTI",
        H: "ORE",
        I: "COSTO ORARIO",
        J: "COSTO PARTECIPANTI",
        K: "TOTALE COSTO PARTECIPANTI",
        L: "DOCENTE",
        M: "ORE",
        N: "COSTO ORARIO",
        O: "COSTO DOCENTE",
        P: "TOTALE COSTO CORSO",
        Q: "CALCOLO CREDITO IMPOSTA (50%)"
      }];

      var ws = X.utils.json_to_sheet(header, {
        header: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"],
        skipHeader: true
      });

      var wb = X.utils.book_new();
      X.utils.book_append_sheet(wb, ws, sheetnames[0]);
      return wb;
    }

    function build_personale(){
      const header = [{
        A: "NOME",
        B: "COGNOME",
        C: "CODICE FISCALE",
        D: "COSTO ORARIO",
        E: "ORE LAVORATIVE",
        F: "INIZIO RAPPORTO",
        G: "FINE RAPPORTO"
      }];

      const wscols = [
        {wpx:100},
        {wpx:100},
        {wch:20},
        {wpx:100},
        {wpx:100},
        {wpx:100},
        {wpx:100}
      ];

      var ws = X.utils.json_to_sheet(header, {
        header: ["A", "B", "C", "D", "E", "F", "G"],
        skipHeader: true
      });

      ws['!cols'] = wscols;

      var formatData = [];
      for(var i=0; i<data.length; i++){
        var persona = data[i];
        var riga = {
          A: persona.nome,
          B: persona.cognome,
          C: persona.codiceFiscale,
          D: persona.costoOrario,
          E: persona.oreLavorative,
          F: persona.inizioRapporto,
          G: persona.fineRapporto
        }
        formatData.push(riga);
      }

      X.utils.sheet_add_json(ws, formatData, {
        skipHeader: true,
        origin: "A2"
      });

      var wb = X.utils.book_new();
      X.utils.book_append_sheet(wb, ws, sheetnames[0]);
      return wb;
    }

    function build_registro_firme(){
      const header = [{
        A: "NOME",
        B: "COGNOME",
        C: "CODICE FISCALE",
        D: "TITOLO CORSO",
        E: "DATA",
        F: "FIRMA",
      }];

      const wscols = [
        {wpx:100},
        {wpx:100},
        {wch:20},
        {wpx:100},
        {wpx:100},
        {wpx:100}
      ];

      var ws = X.utils.json_to_sheet(header, {
        header: ["A", "B", "C", "D", "E", "F"],
        skipHeader: true
      });

      ws['!cols'] = wscols;

      var range = XLSX.utils.decode_range(ws['!ref']);
      var nRows = range.e.r;
      var wsrows = [];
      for(var i=0; i<nRows; i++);

      var formatData = [];
      for(var i=0; i<data.length; i++){
        var persona = data[i];
        var riga = {
          A: persona.nome,
          B: persona.cognome,
          C: persona.codiceFiscale,
          D: persona.costoOrario,
          E: persona.oreLavorative,
          F: persona.inizioRapporto,
          G: persona.fineRapporto
        }
        formatData.push(riga);
      }

      X.utils.sheet_add_json(ws, formatData, {
        skipHeader: true,
        origin: "A2"
      });

      var wb = X.utils.book_new();
      X.utils.book_append_sheet(wb, ws, sheetnames[0]);
      return wb;
    }

    function build_aule(){
      const header = [{
        A: "DESCRIZIONE",
        B: "NUMERO",
        C: "POSTI MASSIMI",
        D: "NUMERO CIVICO",
        E: "CAP",
        F: "PIANO",
        G: "NOTE"
      }];

      const wscols = [
        {wpx:100},
        {wpx:60},
        {wpx:100},
        {wpx:100},
        {wpx:60},
        {wpx:60},
        {wpx:100}
      ];

      var ws = X.utils.json_to_sheet(header, {
        header: ["A", "B", "C", "D", "E", "F", "G"],
        skipHeader: true
      });

      ws['!cols'] = wscols;

      var formatData = [];
      for(var i=0; i<data.length; i++){
        var aula = data[i];
        var riga = {
          A: aula.descrizione,
          B: aula.numero,
          C: aula.postiMax,
          D: aula.numeroCivico,
          E: aula.cap,
          F: aula.piano,
          G: aula.note
        }
        formatData.push(riga);
      }

      X.utils.sheet_add_json(ws, formatData, {
        skipHeader: true,
        origin: "A2"
      });

      var wb = X.utils.book_new();
      X.utils.book_append_sheet(wb, ws, sheetnames[0]);
      return wb;
    }

    function build_riepilogo(){
      /* setto la larghezza delle 32 colonne */
      const wscols = [
        {wpx:100},{wpx:100},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},
        {wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},
        {wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},
        {wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:60},{wpx:100},{wpx:100},{wpx:100}
      ];

      var ws = X.utils.table_to_sheet(data);
      ws['!cols'] = wscols;

      var col = X.utils.decode_col("AC");
      var fmt = '0.00%';

      var range = X.utils.decode_range(ws['!ref']);
      for(var i = range.s.r + 1; i <= range.e.r; i++) {
        /* find the data cell */
        var ref = X.utils.encode_cell({r:i, c:col});
        /* if the particular row did not contain data for the column, the cell will not be generated */
        if(!ws[ref]) continue;
        /* `.t == "n"` for number cells */
        if(ws[ref].t != 'n') continue;
        if(i == 1){
          ws[ref].t = "s";
          ws[ref].v = "%";
          continue;
        }
        /* assign the `.z` number format */
        ws[ref].z = fmt;
      }
      var wb = X.utils.book_new();
      X.utils.book_append_sheet(wb, ws, sheetnames[0]);
      return wb;
    }


    var wbOut;
    switch(purpose) {
      case "personale": wbOut = build_personale(); break;
      case "aule": wbOut = build_aule(); break;
			case "riepilogo": wbOut = build_riepilogo(); break;
			default: console.log("NO PURPOSE MATCH"); return;
		}

    X.writeFile(wbOut, filename + "." + format, {
      bookType: format,
      type: "binary"
    });

    /*
    var fout = X.write(wbOut, {
      bookType: format,
      type: "array"
    });

    var blob = new Blob([fout], {
      type: ''
    });

    write_to_file(filename + "." + format, blob);
    */
  }

  return {
    parseFile: async function(file, format) {
      return new Promise(resolve => {
        var reader = new FileReader();
        var output;
    		reader.onload = function(e) {
    			var data = e.target.result;
    			output = process_wb(X.read(data, { type: 'binary' }), format);
          resolve(output);
    		};

        reader.readAsBinaryString(file);
      });
    },
    exportFile: function(purpose, jsonData, filename, format, sheetnames){
      /* controlli sul formato, filename ecc */
      //document.removeEventListener("deviceready", onDeviceReady, false);
      //document.addEventListener("deviceready", onDeviceReady, false);
      //function onDeviceReady() {
      //    console.log(cordova.file);
          export_wb(purpose, jsonData, filename, format, sheetnames);
      //}
    }
  }
})
