/*!
    * Start Bootstrap - SB Admin v6.0.0 (https://startbootstrap.com/templates/sb-admin)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    (function($) {
    "use strict";

    // Add active state to sidbar nav links
    var path = window.location.href; // because the 'href' property of the DOM element is the absolute path
        $("#layoutSidenav_nav .sb-sidenav a.nav-link").each(function() {
            if (this.href === path) {
                $(this).addClass("active");
            }
        });

    // Toggle the side navigation
    $("#sidebarToggle").on("click", function(e) {
        e.preventDefault();
        $("body").toggleClass("sb-sidenav-toggled");
    });
})(jQuery);

$(document).ready(function(){
    $('.cpf').mask('000.000.000-00', {reverse: true});
  
});

/*confirmar exclusão do crud*/
$(document).ready(function(){
   $('a[data-confirm]').click(function(ev){
       var href = $(this).attr('href');
       if(!$('#modalDelete').length){
           $('body').append('<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true"> <div class="modal-dialog" role="document"> <div class="modal-content"> <div class="modal-header"> <h5 class="modal-title" id="deleteModalLabel">Confirmar exclusão do Registro</h5> <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"> <span aria-hidden="true">&times;</span> </button> </div> <div class="modal-body">Deseja Realmente Excluir o Registro?</div> <div class="modal-footer"> <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button> <a type="button" class="btn btn-primary" id="btnExcluir">Excluir</a> </div> </div> </div> </div>');
       }
       $('#btnExcluir').attr('href', href);
       $('#modalDelete').modal({show: true});
       return false;
   });
});

// Call the dataTables jQuery plugin
$(document).ready(function() {
  $('#dataTable').DataTable({
      language:{
        decimal: ",",
        search: "Pesquisar:",
        emptyTable:     "Lista Vazia",
        info: "Mostrando de _START_ até _END_ de _TOTAL_ ",
        infoEmpty: "Tabela vazia",
        infoFiltered:   "(Filtro entre _MAX_ total)",
        infoPostFix:    "",
        thousands:      ".",
        lengthMenu:     "Mostrar _MENU_",
        loadingRecords: "Carregando...",
        processing:     "Processando...",
        paginate: {
            first:      "Primeiro",
            previous:   "Anterior",
            next:       "Próximo",
            last:       "Último"
        },
        aria: {
            sortAscending:  ": ativar para classificar a coluna ascendente",
            sortDescending: ": ativar para classificar a coluna decrescente"
        }
      }
  });
});


/*buscar municipios*/
function findMunicipioByEstado(id){
    fetch('/municipios/uf/'+id)
            .then(resposta => resposta.json())
            .then(function(response){
                let select = document.getElementById('cidade'); 
                while (select.firstChild) {
                    select.removeChild(select.firstChild);
                }
                let item = document.createElement("option");
                item.value = 0;
                item.innerHTML = '<option value="0" selected>Escolher...</option>';
                select.appendChild(item);
                response.forEach(function(municipio){
                    let item = document.createElement("option");
                    item.value = municipio.idMunicipio;
                    item.innerHTML = '<option value="'+municipio.idMunicipio+'" >'+municipio.nome+'</option>';
                    select.appendChild(item);
                })
            })
            .catch(erro => console.error(erro));
}

/*load telefone cliente*/
function loadTelefoneCliente(){
    var tipo = $("#tipotelefone").val();
    var ddd = $("#ddd").val();
    var numero = $("#numerotelefone").val();
    var obs = $("#obsnumero").val();
    
    var row = "<tr><td>"+tipo+"</td><td>"+ddd+"</td><td>"+numero+"</td><td>"+obs+"</td></tr>";
    $("#tableNumeros tbody").append(row);
    
}

function loadCliente(){
    return {
        razaoSocial: $("#razao").val(),
        nomeFantasia: $("#nome").val(),
        cnpj: $("#cnpj").val(),
        nomeComprador: $("#nomeComprador").val(),
        vendedorId: $("#vendedor").val(),
        email: $("#email").val(),
        regiao: $("#regiao").val(),
        numeroLoja: $("#numeroLoja").val(),
        dataFundacao: $("#dataFundacao").val(),
        sede: $("#sede").val(),
        filial: $("#filial").prop("checked"),
        bloquearVenda: $("#bloquearVenda").prop("checked"),
        exclusivoVendedor: $("#exclusivoVendedor").prop("checked"),
        inativo: $("#inativo").prop("checked"),
        enviarPedidoEmail: $("#enviarPedidoEmail").prop("checked"),
        centroDistribuicao: $("#centroDistribuicao").prop("checked"),
        
        endereco: loadEndereco(),
        telefones: loadTelefone()
    };
}

function loadEndereco(){
    return{
        logradouro: $("#logradouro").val(),
        complemento: $("#complemento").val(),
        numero: $("#numero").val(),
        bairro: $("#bairro").val(),
        cep: $("#cep").val(),
        municipio:{
            idMunicipio: $("#cidade").val(),
        }
    }
}

function loadTelefone(){
    var lista = [];
    $("#tableNumeros tbody>tr").each(function(){
        var $tds = $(this).find('td');
        lista.push({tipoTelefone:$tds.eq(0).text(), ddd:$tds.eq(1).text(),numero:$tds.eq(2).text(), obs:$tds.eq(3).text()});
        
    });
    return lista;
}

function loadRegiao(){
    return{
        nome: $("#nomeRegiao").val(),
        inativo: $("#regiaoInativo").prop("checked")
    };
}

function loadClienteTeste(){
    return{
        razaoSocial: "LEONARDO LOPES",
        nomeFantasia: "NEPOMUCENO SOLUÇÕES",
        cnpj: "010.020.040/0001-10",
        telefones: loadTelefone()
    }
}

function newRegiao(){
    //alert(JSON.stringify( loadRegiao() ));
    fetch("/regioes", {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(loadRegiao())
    }).then(function(response){
        if(response.ok) {
            return response.json();
        } else {
            console.log('Network response was not ok.');
            response.body
              .getReader()
              .read()
              .then(function(data){
                // transformando em string, para
                // visualizarmos melhor
                var fetched = String.fromCharCode.apply(null, data.value);
                alert(fetched);
                $("#alertaErro").addClass("alert").addClass("alert-danger");
                $("#alertaErro").append("<p>"+fetched+"</p>");
            });
        }
    }).then(function(regiao){
      let select = document.getElementById('regiao'); 
        console.log('nova região: '+regiao.nome);
    
        let item = document.createElement("option");
        item.value = regiao.idRegiao;
        item.innerHTML = '<option value="'+regiao.idRegiao+'" >'+regiao.nome+'</option>';
        select.appendChild(item);
              
    })
    .catch(function(error) {
      console.log('There has been a problem with your fetch operation: ' + error.message);
    });
}

function newCliente(){
    alert(JSON.stringify( loadCliente() ) );
    fetch("/clientes", {
    method: "POST",
    headers: {
        'Accept': 'application/json, text/plain, */*',
        'Content-Type': 'application/json'
    },
    body: JSON.stringify(loadCliente())
  }).then(function(response) {
  if(response.ok) {
    return response.blob();
  } else {
    console.log('Network response was not ok.');
    response.body
      .getReader()
      .read()
      .then(function(data){
        // transformando em string, para
        // visualizarmos melhor
        var fetched = String.fromCharCode.apply(null, data.value);
        alert(fetched);
        $("#alertaErro").addClass("alert").addClass("alert-danger");
        $("#alertaErro").append("<p>"+fetched+"</p>");
    });
  }
    })
    .catch(function(error) {
      console.log('There has been a problem with your fetch operation: ' + error.message);
    });
}

function adicionarTelefone() {
    const telefonesDiv = document.getElementById('telefones');
    const novoIndex = telefonesDiv.children.length;
    const novoTelefone = document.createElement('div');

    novoTelefone.setAttribute('data-index', novoIndex);
    novoTelefone.innerHTML = `
        <div class="form-row">
            <input type="hidden" th:field="*{telefones[${novoIndex}].idTelefone}">
            <div class="form-group col-md-3">
                <label for="tipotelefone">Tipo Telefone</label>
                <select class="form-control" name="telefones[${novoIndex}].tipoTelefone" >
                    <option value="FONE">Fone</option>
                    <option value="CELULAR">Celular</option>
                </select>
            </div>
            <div class="form-group col-md-4">
                <label for="numerotelefone">Número</label>
                <div class="form-row align-items-left">
                    <input type="text" class="form-control col-md-3" name="telefones[${novoIndex}].ddd" placeholder="DDD" data-mask="00" autocomplete="off" >
                    <input type="text" class="form-control col-md-8" name="telefones[${novoIndex}].numero" placeholder="0000-0000" data-mask="0000-0000" autocomplete="off">
                </div>
            </div>
            <div class="form-group col-md-4">
                <label for="obsnumero">Observação</label>
                <div class="row g-2">
                    <input type="text" class="form-control col-sm" name="telefones[${novoIndex}].obs" autocomplete="off" >
                    <button type="button" class="btn btn-secondary btn-sm" onclick="removerTelefone(this)">
                        <i class="fa fa-trash"></i>
                    </button>
                </div>
            </div>
        </div>
    `;
    telefonesDiv.appendChild(novoTelefone);
}
// Função para remover um telefone
function removerTelefone(button) {
    button.parentElement.remove();
    atualizarIndices();
}

// Atualiza os índices após remoção
function atualizarIndices() {
    const telefonesDiv = document.getElementById('telefones');
    Array.from(telefonesDiv.children).forEach((div, index) => {
        div.setAttribute('data-index', index);
        const input = div.querySelector('input[name]');
        input.name = `telefones[${index}].numero`;
    });
}

function adicionarCondicoesPgto() {
    const condicoesDiv = document.getElementById('condicoes');
    const novoIndex = condicoesDiv.children.length;
    const novoCondicoes = document.createElement('div');

    novoCondicoes.setAttribute('data-index', novoIndex);
    novoCondicoes.innerHTML = `
        <input type="hidden" th:field="*{condicoesPagamento[${novoIndex}].idCondicoesPagamento}">
        <div class="row mb-3 g-3 align-items-center">
            <label class="col-sm-2 col-form-label">Descrição:</label>
            <input type="text" class="form-control col-md-8" name="condicoesPagamento[${novoIndex}].nome">
            <button type="button" class="btn btn-secondary btn-sm col-auto" onclick="removerCondicoes(this)">
                <i class="fa fa-trash"></i>
            </button>
        </div>
    `;
    condicoesDiv.appendChild(novoCondicoes);
}


// Função para remover uma condição de pagamento
function removerCondicoes(button) {
    button.parentElement.remove();
    atualizarIndicesCondicoes();
}

// Atualiza os índices após remoção
function atualizarIndicesCondicoes() {
    const condicoesDiv = document.getElementById('condicoes');
    Array.from(condicoesDiv.children).forEach((div, index) => {
        div.setAttribute('data-index', index);
        const input = div.querySelector('input[name]');
        input.name = `condicoesPagamento[${index}].nome`;
    });
}

