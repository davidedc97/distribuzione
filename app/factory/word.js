angular.module('myApp.wordModule', [])

.factory('factoryWord', function(){
  const newLine = new docx.Paragraph({children:[]});
  const newPage = new docx.PageBreak();
  const emptyCell = new docx.TableCell({
    verticalAlign: "center",
    children: [new docx.Paragraph({
      alignment: "center",
      children: [new docx.TextRun({text: ""})]
    })]
  });

  function save_doc(doc, filename){
    docx.Packer.toBlob(doc).then(blob => {
      console.log(blob);
      saveAs(blob, filename +".docx");
      console.log("Document created successfully");
    });
  }

  function build_calendario(mappaDati){
    const tableHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 1000,
        rule: docx.HeightRule.ATLEAST
      },
      children: [
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "Data",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "Dalle-alle ore",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "Dalle-alle ore",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "Docente/i",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "Sede corso",
                bold: true,
                size: 24
              })
            ]
          })]
        })
      ]
    });
    let tableRows = [];
    tableRows.push(tableHeader);

    for(var i=0; i<mappaDati.dateCorso.length; i++){
      let data = mappaDati.dateCorso[i];
      let currentRow = new docx.TableRow({
        height: {
          height: 1000,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.data,
                  bold: true,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.orePrimo,
                  bold: true,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.oreSecondo,
                  bold: true,
                  size: 22
                })
              ]
            })]
          })
        ]
      });
      if(i==0){ //settiamo sulla prima riga docente/i e sede
        let nomiDocenti = "";
        for(var j=0; j<mappaDati.listaDocenti.length; j++){
          let docente = mappaDati.listaDocenti[j].cognome + " " + mappaDati.listaDocenti[j].nome;
          nomiDocenti += docente;
          // se non sto aggiungendo l'ultimo docente, li separo con una virgola
          if(j != mappaDati.listaDocenti.length-1) nomiDocenti += ", ";
        }
        var cellDocente = new docx.TableCell({
          verticalAlign: "center",
          rowSpan: mappaDati.dateCorso.length,
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: nomiDocenti,
                bold: true,
                size: 22
              })
            ]
          })]
        });
        var cellSede = new docx.TableCell({
          verticalAlign: "center",
          rowSpan: mappaDati.dateCorso.length,
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: mappaDati.sedeCorso,
                bold: true,
                size: 22
              })
            ]
          })]
        });
        currentRow.addCellToIndex(cellDocente, 3);
        currentRow.addCellToIndex(cellSede, 4);
      }
      else{
        var cellEmpty = new docx.TableCell({
          children: []
        });
        currentRow.addCellToIndex(cellEmpty, 3);
        currentRow.addCellToIndex(cellEmpty, 4);
      }
      tableRows.push(currentRow);
    }

    let doc = new docx.Document();
    doc.addSection({
      properties: {},
      children: [
        new docx.Paragraph({
          children: [
            new docx.TextRun({
              text: "Soggetto organizzatore : ",
              bold: true,
              size: 22
            }),
            new docx.TextRun({
              text: mappaDati.soggOrg,
              size: 22
            })
          ]
        }), newLine,
        new docx.Paragraph({
          children: [
            new docx.TextRun({
              text: "Titolo progetto : ",
              bold: true,
              size: 22
            }),
            new docx.TextRun({
              text: mappaDati.titoloProg,
              size: 22
            })
          ]
        }), newLine,
        new docx.Paragraph({
          children: [
            new docx.TextRun({
              text: "ID corso : ",
              bold: true,
              size: 22
            }),
            new docx.TextRun({
              text: mappaDati.idCorso,
              size: 22
            })
          ]
        }), newLine,
        new docx.Paragraph({
          children: [
            new docx.TextRun({
              text: "Durata progetto : ",
              bold: true,
              size: 22
            }),
            new docx.TextRun({
              text: mappaDati.durataOraria + " ore",
              size: 22
            })
          ]
        }), newLine, newLine,
        new docx.Paragraph({
          children: [
            new docx.TextRun({
              text: "CALENDARIO",
              bold: true,
              size: 24
            })
          ],
          alignment: "center"
        }), newLine,
        new docx.Table({
          width: {
            size: 9000,
            type: docx.WidthType.DXA
          },
          rows: tableRows
        })
      ]
    });
    return doc;
  }


  function build_registro_didattico_presenze(mappaDati){
    let doc = new docx.Document();
    let headerTable = new docx.Table({
      width: {
        size: 9000,
        type: docx.WidthType.DXA
      },
      rows: [
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Soggetto organizzatore",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.soggOrg,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Titolo corso formativo",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.titoloProg,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Sede di svolgimento attività",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.sedeCorso,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Data inizio corso",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.dateCorso[0].data,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Data fine corso",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.dateCorso[mappaDati.dateCorso.length-1].data,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Durata complessiva corso",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.durataOraria + " ORE",
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        })
      ]
    });

    let docentiRows = [];
    let docentiHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 800,
        rule: docx.HeightRule.ATLEAST
      },
      children:[
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "COGNOME E NOME",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "FIRMA",
                bold: true,
                size: 24
              })
            ]
          })]
        })
      ]
    });

    docentiRows.push(docentiHeader);

    for(var i=0; i<mappaDati.listaDocenti.length; i++){
      let docente = mappaDati.listaDocenti[i];
      let currentRow = new docx.TableRow({
        height: {
          height: 800,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: docente.cognome + " " + docente.nome,
                  //bold: true,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: "",
                  bold: true,
                  size: 22
                })
              ]
            })]
          })
        ]
      });
      docentiRows.push(currentRow);
    }

    let docentiTable = new docx.Table({
      width: {
        size: 9000,
        type: docx.WidthType.DXA
      },
      rows: docentiRows
    });

    let partecipantiRows = [];
    let partecipantiHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 800,
        rule: docx.HeightRule.ATLEAST
      },
      children:[
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "NUMERO",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "COGNOME E NOME",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "FIRMA",
                bold: true,
                size: 24
              })
            ]
          })]
        })
      ]
    });

    partecipantiRows.push(partecipantiHeader);

    for(var i=0; i<mappaDati.listaPartecipanti.length; i++){
      let partecipante = mappaDati.listaPartecipanti[i];
      let currentRow = new docx.TableRow({
        height: {
          height: 800,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: (i+1).toString(),
                  bold: true,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: partecipante.cognome + " " + partecipante.nome,
                  //bold: true,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: "",
                  bold: true,
                  size: 22
                })
              ]
            })]
          })
        ]
      });
      partecipantiRows.push(currentRow);
    }

    let partecipantiTable = new docx.Table({
      width: {
        size: 9000,
        type: docx.WidthType.DXA
      },
      rows: partecipantiRows
    });

    doc.addSection({
      properties:{},
      children:[
        new docx.Paragraph({
          alignment: "center",
          children: [
            new docx.TextRun({
              text: "REGISTRO DIDATTICO DI PRESENZA",
              bold: true,
              size: 26,
              heading: "Title"

            })
          ]
        }), newLine,
        headerTable,
        new docx.Paragraph({
          pageBreakBefore: true,
          alignment: "center",
          children: [
            new docx.TextRun({
              text: "ELENCO DEI DOCENTI E FIRME CAMPIONE",
              bold: true,
              size: 26,
              heading: "Title"

            })
          ]
        }), newLine,
        docentiTable, newLine, newLine,
        new docx.Paragraph({
          alignment: "center",
          children: [
            new docx.TextRun({
              text: "ELENCO DEI PARTECIPANTI E FIRME CAMPIONE",
              bold: true,
              size: 26,
              heading: "Title"

            })
          ]
        }), newLine,
        partecipantiTable
      ]
    });

    let presenzeRows = [];
    let presenzeHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 800,
        rule: docx.HeightRule.ATLEAST
      },
      children:[
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "ORA ENTRATA",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "FIRMA",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "ORA USCITA",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "FIRMA",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "NUMERO",
                bold: true,
                size: 24
              })
            ]
          })]
        })
      ]
    });

    presenzeRows.push(presenzeHeader);

    for(var i=0; i<mappaDati.listaPartecipanti.length; i++){
      let currentRow = new docx.TableRow({
        height: {
          height: 800,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          emptyCell,
          emptyCell,
          emptyCell,
          emptyCell,
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: (i+1).toString(),
                  bold: true,
                  size: 22
                })
              ]
            })]
          })
        ]
      });

      presenzeRows.push(currentRow);
    }

    let presenzeTable = new docx.Table({
      width: {
        size: 9000,
        type: docx.WidthType.DXA
      },
      rows: presenzeRows
    });

    let totalePresenzeTable = new docx.Table({
      width: {
        size: 4000,
        type: docx.WidthType.DXA
      },
      rows: [
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "TOTALE PRESENZE N.",
                    bold: true,
                    size: 22
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "",
                    bold: true,
                    size: 22
                  })
                ]
              })]
            })
          ]
        })
      ]
    });

    let totaleAssenzeTable = new docx.Table({
      width: {
        size: 4000,
        type: docx.WidthType.DXA
      },
      rows: [
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "TOTALE ASSENZE N.",
                    bold: true,
                    size: 22
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "",
                    bold: true,
                    size: 22
                  })
                ]
              })]
            })
          ]
        })
      ]
    });

    for(var i=0; i<mappaDati.dateCorso.length; i++){
      let data = mappaDati.dateCorso[i];
      doc.addSection({
        properties:{},
        children:[
          new docx.Paragraph({
            pageBreakBefore: true,
            alignment: "center",
            children: [
              new docx.TextRun({
                text: "REGISTRO PRESENZE DEL " + data.data,
                bold: true,
                size: 26,
                heading: "Title"

              })
            ]
          }), newLine,
          presenzeTable, newLine, newLine,
          totalePresenzeTable, newLine,
          totaleAssenzeTable, newLine,
          new docx.Paragraph({
            alignment: "right",
            children: [
              new docx.TextRun({
                text: "FIRMA DOCENTE/I\t\t\t",
                bold: true,
                size: 24
              })
            ]
          })

        ]
      })
    }

    return doc;
  }

  function build_attestati(mappaDati){

    let headerTable = new docx.Table({
      width: {
        size: 8000,
        type: docx.WidthType.DXA
      },
      alignment: "center",
      rows: [
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Soggetto organizzatore",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.soggOrg,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Sede legale",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.sedeLegale,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Sede del corso",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.sedeCorso,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        }),
        new docx.TableRow({
          height: {
            height: 800,
            rule: docx.HeightRule.ATLEAST
          },
          children:[
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: "Titolo corso formativo",
                    bold: true,
                    size: 24
                  })
                ]
              })]
            }),
            new docx.TableCell({
              verticalAlign: "center",
              children: [new docx.Paragraph({
                alignment: "center",
                children:[
                  new docx.TextRun({
                    text: mappaDati.titoloProg,
                    bold: false,
                    size: 22
                  })
                ]
              })]
            })
          ]
        })
      ]
    });

    let doc = new docx.Document();
    for(var i=0; i<mappaDati.listaPartecipanti.length; i++){

      let partecipante = mappaDati.listaPartecipanti[i];

      let textParag = new docx.Paragraph({
        alignment: "left",
        spacing:{
          line: 350
        },
        children: [
          new docx.TextRun({
            text: "che ",
            size: 22
          }),
          new docx.TextRun({
            text: partecipante.cognome + " " + partecipante.nome,
            allCaps: true,
            bold: true,
            size: 24
          }),
          new docx.TextRun({
            text: " ha frequentato con profitto il corso ",
            size: 22
          }),
          new docx.TextRun({
            text: mappaDati.titoloProg,
            allCaps: true,
            bold: true,
            size: 24
          }),
          new docx.TextRun({
            text: " per n. " + mappaDati.durataOraria + " ore, tenutosi a ",
            size: 22
          }),
          new docx.TextRun({
            text: mappaDati.sedeCorso,
            size: 22
          }),
          new docx.TextRun({
            text: " nei giorni: ",
            size: 22
          })
        ]
      });

      textParag.root.push(new docx.TextRun({
        text: mappaDati.dateCorso[0].data,
        size: 22
      }));

      for(var j=1; j<mappaDati.dateCorso.length; j++){
        let dateText = new docx.TextRun({
          text: ", " + mappaDati.dateCorso[j].data,
          size: 22
        });

        textParag.root.push(dateText);
      }

      textParag.root.push(new docx.TextRun({
        text: ".",
        size: 22
      }));


      doc.addSection({
        properties:{},
        children:[
          new docx.Paragraph({
            pageBreakBefore: true,
            alignment: "center",
            children: [
              new docx.TextRun({
                text: "ATTESTATO DI FORMAZIONE DEL PERSONALE",
                bold: true,
                size: 28,
                heading: "Title",
                color: "FF0000"
              })
            ]
          }), newLine, newLine,
          headerTable, newLine, newLine,
          new docx.Paragraph({
            alignment: "center",
            children: [
              new docx.TextRun({
                text: "SI ATTESTA",
                bold: true,
                size: 28,
                heading: "Title",
                color: "FF0000"
              })
            ]
          }), newLine,
          textParag
        ]
      })

    }

    return doc;
  }

  function build_relazione_docente(mappaDati){

    let doc = new docx.Document();

    // la libreria non ammette l'escape \n, quindi purtroppo tocca fare così
    let p1_text1 = 'La presente relazione riguarda la chiusura del corso di formazione "';
    let p1_text2 = '", dei dipendenti della ditta ';
    let p1_text3 = 'La valutazione finale è positiva in relazione ai singoli aspetti generali del corso.';
    let p1_text4 = 'Le attività di formazione si sono svolte durante il periodo dal ' + mappaDati.dateCorso[0].data + ' fino al ' + mappaDati.dateCorso[mappaDati.dateCorso.length-1].data + '.';
    let p1_text5 = 'Gli allievi hanno interagito con molto interesse alle attività proposte, per la materia, sviluppando senso critico e applicabilità dei programmi svolti alle proprie attività lavorative.';
    let p1_text6 = 'Il corso si è sviluppato per un totale di n. ' + mappaDati.durataOraria + ' ore secondo le seguenti modalità formative:';
    let p1_text7 = mappaDati.modalitaFormative;

    let p1 = new docx.Paragraph({
      spacing:{
        line: 350
      },
      alignment: "left",
      children:[
        new docx.TextRun({
          text: p1_text1,
          size: 22
        }),
        new docx.TextRun({
          text: mappaDati.titoloProg,
          size: 22,
          bold: true
        }),
        new docx.TextRun({
          text: p1_text2,
          size: 22
        }),
        new docx.TextRun({
          text: mappaDati.soggOrg,
          size: 22,
          bold: true
        }),
        new docx.TextRun({
          text: ".",
          size: 22
        }),
        new docx.TextRun({
          text: p1_text3,
          size: 22
        }).break(),
        new docx.TextRun({
          text: p1_text4,
          size: 22
        }).break(),
        new docx.TextRun({
          text: p1_text5,
          size: 22
        }).break(),
        new docx.TextRun({
          text: p1_text6,
          size: 22
        }).break(),
        new docx.TextRun({
          text: p1_text7,
          size: 22
        }).break()
      ]
    });

    let oreRows = [];

    let oreHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 800,
        rule: docx.HeightRule.ATLEAST
      },
      children:[
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "DATA",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "DALLE - ALLE",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "DALLE - ALLE",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "ATTIVITà SVOLTE",
                bold: true,
                size: 24,
                allCaps: true
              })
            ]
          })]
        })
      ]
    });

    oreRows.push(oreHeader);

    for(var i=0; i<mappaDati.dateCorso.length; i++){
      let data = mappaDati.dateCorso[i];

      let currentRow = new docx.TableRow({
        tableHeader: true,
        height: {
          height: 800,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.data,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.orePrimo,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.oreSecondo,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: data.attivitaSvolte,
                  size: 22,
                })
              ]
            })]
          })
        ]
      });

      oreRows.push(currentRow);

    }

    let tableOre = new docx.Table({
      width: {
        size: 8000,
        type: docx.WidthType.DXA
      },
      alignment: "center",
      rows: oreRows
    });

    let valutazioneRows = [];

    let valutazioneHeader = new docx.TableRow({
      tableHeader: true,
      height: {
        height: 800,
        rule: docx.HeightRule.ATLEAST
      },
      children:[
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "DIPENDENTE",
                bold: true,
                size: 24
              })
            ]
          })]
        }),
        new docx.TableCell({
          verticalAlign: "center",
          shading: {
              fill: "f0f2f4",
              val: docx.ShadingType.PERCENT_10,
              color: "clear",
          },
          children: [new docx.Paragraph({
            alignment: "center",
            children:[
              new docx.TextRun({
                text: "VALUTAZIONE",
                bold: true,
                size: 24
              })
            ]
          })]
        })
      ]
    });

    valutazioneRows.push(valutazioneHeader);

    for(var i=0; i<mappaDati.listaPartecipanti.length; i++){
      let partecipante = mappaDati.listaPartecipanti[i];

      let currentRow = new docx.TableRow({
        tableHeader: true,
        height: {
          height: 800,
          rule: docx.HeightRule.ATLEAST
        },
        children:[
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: partecipante.cognome + " " + partecipante.nome,
                  size: 22
                })
              ]
            })]
          }),
          new docx.TableCell({
            verticalAlign: "center",
            children: [new docx.Paragraph({
              alignment: "center",
              children:[
                new docx.TextRun({
                  text: "",
                  size: 22
                })
              ]
            })]
          })
        ]
      });

      valutazioneRows.push(currentRow);

    }

    let tableValutazione = new docx.Table({
      width: {
        size: 4000,
        type: docx.WidthType.DXA
      },
      alignment: "left",
      rows: valutazioneRows
    });

    doc.addSection({
      properties:{},
      children:[
        new docx.Paragraph({
          alignment: "center",
          children: [
            new docx.TextRun({
              text: 'RELAZIONE DEL DOCENTE SULLA VALUTAZIONE DELL’ATTIVITA’ DEL CORSO DI FORMAZIONE "' + mappaDati.titoloProg + '"',
              bold: true,
              size: 28,
              heading: "Title",
            })
          ]
        }), newLine,
        p1, newLine,
        new docx.Paragraph({
          alignment: "left",
          children: [
            new docx.TextRun({
              text: "Le ore sono state così distribuite:",
              size: 22,
            })
          ]
        }), newLine,
        tableOre, newLine,
        new docx.Paragraph({
          alignment: "left",
          pageBreakBefore: true,
          children: [
            new docx.TextRun({
              text: "I risultati dei test hanno evidenziato i seguenti risultati:",
              size: 22
            })
          ]
        }), newLine,
        tableValutazione, newLine,
        new docx.Paragraph({
          alignment: "left",
          spacing:{
            line: 350
          },
          pageBreakBefore: true,
          children: [
            new docx.TextRun({
              text: "Sulla base della risposta dei corsisti si ritiene che gli interventi e le attività formative, i contenuti, le attività svolte , gli strumenti utilizzati, i risultati ottenuti, il clima relazionale  tra corsisti e docente, la costanza nella partecipazione e la disponibilità all’apprendimento da parte degli allievi, la motivazione, nonché le competenze e le  conoscenze acquisite siano state adeguate ai loro bisogni e alle loro aspettative.",
              size: 22
            })
          ]
        }), newLine, newLine,
        new docx.Paragraph({
          alignment: "right",
          children: [
            new docx.TextRun({
              text: "FIRMA DOCENTE/I\t\t\t",
              bold: true,
              size: 24
            })
          ]
        })
      ]
    });

    /* esempi dalla wiki di docx.js
    const table3 = new docx.Table({
    alignment: docx.AlignmentType.CENTER,
    rows: [
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("Foo")],
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("v")],
                    columnSpan: 3,
                }),
            ],
        }),
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("Bar1")],
                    shading: {
                        fill: "b79c2f",
                        val: docx.ShadingType.PERCENT_10,
                        color: "clear",
                    },
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("Bar2")],
                    shading: {
                        fill: "42c5f4",
                        val: docx.ShadingType.PERCENT_95,
                        color: "clear",
                    },
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("Bar3")],
                    shading: {
                        fill: "880aa8",
                        val: docx.ShadingType.PERCENT_10,
                        color: "e2df0b",
                    },
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("Bar4")],
                    shading: {
                        fill: "FF0000",
                        val: docx.ShadingType.CLEAR,
                        color: "clear",
                    },
                }),
            ],
        }),
    ],
    width: {
        size: 7000,
        type: docx.WidthType.DXA,
    },
    margins: {
        top: 400,
        bottom: 400,
        right: 400,
        left: 400,
    },
});

const table4 = new docx.Table({
    rows: [
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("0,0")],
                    columnSpan: 2,
                }),
            ],
        }),
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("1,0")],
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("1,1")],
                }),
            ],
        }),
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("2,0")],
                    columnSpan: 2,
                }),
            ],
        }),
    ],
    width: {
        size: 100,
        type: docx.WidthType.PERCENTAGE,
    },
});

const table5 = new docx.Table({
    rows: [
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [new docx.Paragraph("0,0")],
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("0,1")],
                    rowSpan: 2,
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("0,2")],
                }),
            ],
        }),
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [],
                }),
                new docx.TableCell({
                    children: [new docx.Paragraph("1,2")],
                    rowSpan: 2,
                }),
            ],
        }),
        new docx.TableRow({
            children: [
                new docx.TableCell({
                    children: [],
                }),
                new docx.TableCell({
                    children: [],
                }),
            ],
        }),
    ],
    width: {
        size: 100,
        type: docx.WidthType.PERCENTAGE,
    },
});

doc.addSection({
    children: [
        table3,
        new docx.Paragraph("Merging columns"),
        table4,
        new docx.Paragraph("More Merging columns"),
        table5,
    ],
});
*/
    return doc;

  }

  return {
    exportFile: function(purpose, jsonData, filename){
      let doc;
      switch(purpose) {
        case "calendario": {doc = build_calendario(jsonData); break;}
        case "presenze": {doc = build_registro_didattico_presenze(jsonData); break;}
        case "relazione": {doc = build_relazione_docente(jsonData); break;}
        case "attestati": {doc = build_attestati(jsonData); break;}
  			default: console.log("NO PURPOSE MATCH"); return;
  		}
      save_doc(doc, filename);
    }
  }

})
