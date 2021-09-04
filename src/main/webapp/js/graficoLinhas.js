$('document').ready(function () {
    $.ajax({
        type: "GET",
        url: 'http://localhost:8080/ellentex-livrariales/admin/Pesquisa',
        dataType: "json",
        data: {
            operacao: "RELATORIO_LIVRO",
            dataInicio: document.getElementById('dataInicio').value,
            dataFim: document.getElementById('dataFim').value

        },
        success: function (responseText) {

            //console.log("JSON recebido = " + responseText);
            let jsonString = JSON.stringify(responseText);
            let json = responseText;
            let arrayJson = [];
            let periodo=[];
            let qtdeElementoArray;

            //JSON EM ARRAY
            for(let key of Object.keys(json)){
                arrayJson.push([key,json[key]]
                )
            }

            console.log(json)
            for(let key of Object.keys(json)) {
                console.log(key)
            }

            for(let key of Object.keys(json)){
                qtdeElementoArray = json[key].length;
            }

            //LABEL PERÍODO
            for(let key of Object.keys(json)) {
                let data = key.split('-').reverse().join('/');
                periodo.push(key.split('-').reverse().join('/'));
                console.log(data);
            }
            grafico(periodo, json,qtdeElementoArray);
        }
    });
})

function grafico(periodo, json, qtdeElementoArray){

    let ctx = document.getElementById('canvas').getContext('2d');
    let myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: periodo,

        },
        options: {
            plugins:{
                title: {
                    display: true,
                    text: 'Mais Vendidos'
                }
            },
            interaction: {
                mode: 'index',
                intersect: false,
            },
            scales: {
            },

            legend: {
                position: 'top',
            },
            elements:{
                line: {
                    tension: 0.2
                }
            }
        }
    });



    for(let i = 0; i < qtdeElementoArray; i++) {
        let array =[];
        let titulo = "";

        for (key of Object.keys(json)) {
/*            if(json[key][i].qtde === 0){
                array.push(0);
            }
            else {
                array.push(json[key][i].qtde);
            }*/

            array.push(json[key][i].qtde);
            titulo = json[key][i].titulo;
        }

        let cor = gerar_cor(1);

        const newDataset = {
            label: titulo,
            backgroundColor: [cor],
            borderColor: [cor],
            data: array,
            borderWidth: 2
        };

        myChart.data.datasets.push(newDataset);
    }


    myChart.update();

}

function gerar_cor(opacidade = 1) {

    let r = Math.random() * 255;
    let g = Math.random() * 255;
    let b = Math.random() * 255;

    return `rgba(${r}, ${g}, ${b}, ${opacidade})`;

}

function toDateISO(dateStr) {
    return dateStr.split('/').reverse().join('-');
}
