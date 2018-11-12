const FiltroCheckbox = (props) => {
  return (
    <div className="filtro">
      <label className="switch">
        <input type="checkbox" 
               checked={props.filtro.mostrar}
               onChange={() => {props.toggleFiltro(props.filtro.flTipo)}}/>
        <span className="slider"></span>
      </label>
      <span>{props.filtro.nome}</span>
    </div>
  );
}

const FiltroTexto = (props) => {
  return (
    <div className="filtro-texto">
      <span>Ponto de Venda:</span>
      <input type="text"
             placeholder="Ponto de venda"
             value={ props.filtroPontoDeVenda }
             onChange={ props.modificadorFiltro } />
    </div>
  );
}

const Filtros = (props) => {
  const filtros = props.filtros.map((filtro) => {
    return <FiltroCheckbox
            key={filtro.flTipo}
            filtro={filtro}
            toggleFiltro={props.toggleFiltro} />
  });
  return (
    <div className="card-filtros">
      <span className="subtitulo">Filtros</span>
      <FiltroTexto
        filtroPontoDeVenda={props.filtroPontoDeVenda}
        modificadorFiltro={props.modificadorFiltro} />
      {filtros}
    </div>
  );
}

const Alerta = (props) => {
  let produto = null;
  if (props.alerta.produto != null) {
    produto = <p><span>Produto: </span>{props.alerta.produto}</p>
  }
  let categoria = null;
  if (props.alerta.categoria != null) {
    categoria = <p><span>Categoria: </span>{props.alerta.categoria}</p>
  }
  let margem = null;
  if (props.alerta.margem != null) {
    margem = <p><span>Margem: </span>{props.alerta.margem}</p>
  }
  return (
    <div className="card-alerta">
      <p><span>Ponto de Venda: </span>{props.alerta.pontoDeVenda}</p>
      {produto}
      {categoria}
      <p><span>Descrição: </span>{props.alerta.descricao}</p>
      {margem}
    </div>
  );
}

class App extends React.Component {

  state = {
    alertas: [
      {
        id: 1,
        pontoDeVenda: "Padaria do seu João",
        descricao: "Preço abaixo do estipulado!",
        produto: "Ovo de Pascoa Kinder 48",
        categoria: null,
        flTipo: 3,
        margem: 5
      },
      {
        id: 2,
        pontoDeVenda: "Padaria do Alemão",
        descricao: "Preço acima do estipulado!",
        produto: "Ovo de Pascoa Barbie 48",
        categoria: null,
        flTipo: 2,
        margem: -5
      },
      {
        id: 3,
        pontoDeVenda: "Padaria do Alemão",
        descricao: "Participação inferior à estipulada!",
        produto: null,
        categoria: "Refrigerantes",
        flTipo: 5,
        margem: 2
      },
      {
        id: 4,
        pontoDeVenda: "Angel One Capoeiras",
        descricao: "Ruptura detectada!",
        produto: "Ovo de Pascoa Diamante Negro 48",
        categoria: null,
        flTipo: 1,
        margem: null
      },
      {
        id: 5,
        pontoDeVenda: "Angel One Capoeiras",
        descricao: "Ruptura detectada!",
        produto: "Ovo de Pascoa Laka 48",
        categoria: null,
        flTipo: 1,
        margem: null
      },
      {
        id: 6,
        pontoDeVenda: "Angel One Capoeiras",
        descricao: "Ruptura detectada!",
        produto: "Ovo de Pascoa NeymarJr 48",
        categoria: null,
        flTipo: 1,
        margem: null
      },
      {
        id: 7,
        pontoDeVenda: "Angel One Capoeiras",
        descricao: "Ruptura detectada!",
        produto: "Ovo de Pascoa Crunch 48",
        categoria: null,
        flTipo: 1,
        margem: null
      },
      {
        id: 8,
        pontoDeVenda: "Padaria do Alemão",
        descricao: "Participação superior à estipulada!",
        produto: null,
        categoria: "Sabonetes",
        flTipo: 4,
        margem: -2
      },
      {
        id: 9,
        pontoDeVenda: "Padaria do Alemão",
        descricao: "Participação inferior à estipulada!",
        produto: null,
        categoria: "Shampoo",
        flTipo: 5,
        margem: 20
      }
    ],
    filtros: [
      {
        nome: "Ruptura",
        flTipo: 1,
        mostrar: true
      },
      {
        nome: "Preço alto",
        flTipo: 2,
        mostrar: true
      },
      {
        nome: "Preço baixo",
        flTipo: 3,
        mostrar: true
      },
      {
        nome: "Participação alta",
        flTipo: 4,
        mostrar: true
      },
      {
        nome: "Participação baixa",
        flTipo: 5,
        mostrar: true
      },
    ],
    filtroPontoDeVenda: ""
  }

  toggleFiltroHandler = (flTipo) => {
    const novosFiltros = [...this.state.filtros];
    const index = novosFiltros.findIndex(x => x.flTipo === flTipo);
    novosFiltros[index].mostrar = !this.state.filtros[index].mostrar;
    const novoEstado = {filtros: novosFiltros};
    this.setState(novoEstado);
  }

  mudarFiltroPontoDeVendaHandler = (event) => {
    this.setState({filtroPontoDeVenda: event.target.value});
  }

  render() {
    const alertas = this.state.alertas.map((alerta) => {
      const mostraPontoDeVenda = alerta.pontoDeVenda.toLowerCase().includes(this.state.filtroPontoDeVenda.toLowerCase());
      const mostraTipo = this.state.filtros.find(x => x.flTipo === alerta.flTipo).mostrar;
      if (mostraPontoDeVenda && mostraTipo) {
        return <Alerta alerta={alerta} key={alerta.id} />;
      }
    });
    return (
      <div>
        <Filtros
          filtroPontoDeVenda={this.state.filtroPontoDeVenda}
          filtros={this.state.filtros}
          modificadorFiltro={this.mudarFiltroPontoDeVendaHandler}
          toggleFiltro={this.toggleFiltroHandler} />
        <span className="subtitulo">Alertas</span>
      { alertas }
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById('root'));