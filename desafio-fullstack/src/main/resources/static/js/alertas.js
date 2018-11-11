const FiltroCheckbox = (props) => {
  return (
    <div>
      <span>{props.filtro.nome}</span>
      <input type="checkbox" 
             checked={props.filtro.mostrar}
             onChange={() => {props.toggleFiltro(props.filtro.flTipo)}}/>
    </div>
  );
}

const FiltroTexto = (props) => {
  return (
    <input type="text"
           value={ props.filtroPontoDeVenda }
           onChange={ props.modificadorFiltro } />
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
    <div>
      <FiltroTexto
        filtroPontoDeVenda={props.filtroPontoDeVenda}
        modificadorFiltro={props.modificadorFiltro} />
      {filtros}
    </div>
  );
}

const Alerta = (props) => {
  return (
    <div className="card">
      <div>{props.alerta.produto}</div>
      <div>{props.alerta.pontoDeVenda}</div>
      <div>{props.alerta.descricao}</div>
      <div>{props.alerta.categoria}</div>
      <div>{props.alerta.margem}</div>
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
      { alertas }
      </div>
    );
  }
}

ReactDOM.render(<App />, document.getElementById('root'));