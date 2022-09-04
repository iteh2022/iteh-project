import React from 'react'
import user from '../components/pages/Login.js'

class PrijavaPredmeta extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            predmeti: [],
            isApiError: false
        }
    }
    componentDidMount() {
        fetch("http://localhost:8080/api/prijava-predmeta")
        .then(res => res.json())
        .then(
            console.log('ddd'),
            (result) => {
                this.setState({
                    predmeti: result
                });
            },
            (error) => {
                this.setState({isApiError:true});
            }
        )
    }
    render() {
        var predmetiLista = this.state.predmeti;
        // debugger;
        if(predmetiLista  && predmetiLista.length > 0){
            return(<div>
                <h2>Prijavljeni predmeti</h2>
                <table className='table'>
                    <thead>
                        <tr>
                            <th>Naziv predmeta</th>
                        </tr>
                    </thead>
                    <tbody>
                        {predmetiLista.map(pr => (
                            <tr >
                                <td>{pr.naziv}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>)
        }
        else{
            return(<div>Nema prijavljenih predmeta</div>)
        }
    }
}
export default PrijavaPredmeta;