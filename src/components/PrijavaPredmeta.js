import React, { useContext, useEffect, useState } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import { Link } from 'react-router-dom'
import { ApiContext, CurrentUserContext } from '../App'
import axios from "axios"

function PrijavaPredmeta() {
    const [predmeti, setPredmeti] = useState([])
    const { api } = useContext(ApiContext)
    const { currentUser } = useContext(CurrentUserContext)

    function prijaviPredmet(predmet) {
        axios.post(`${api}/prijava-predmeta`, {predmet: predmet.naziv},
         {
            headers: {
                Authorization: `Bearer ${currentUser}`
            }
        }).then(response => {
            alert("Uspesno prijavljen ispit!")
        }).catch((error) => {
            console.log(error);
            alert("Izabrani predmet je vec prijavljen!")
        });
    }

    useEffect(() => {
        axios.get(`${api}/predmet/all`, {
            headers: {
                'Authorization': `Bearer ${currentUser}`
            }
        }).then(response => {
            setPredmeti(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }, [api, currentUser]);
    
    return (
        <Row className='mt-5'>
            <Col sm={12}>
                <Card className='shadow-sm payment-card'>
                    <Card.Body>
                        <h5 className="d-flex align-items-center mb-3">Predmeti</h5>
                            {predmeti && predmeti.length > 0 ? <Table striped bordered hover className='text-center'>
                            <thead>
                                <tr className='text-center'>
                                    <th>Naziv predmeta</th>
                                </tr>
                            </thead>
                            <tbody>
                                {predmeti.map((predmet, index) => (
                                    <tr key={index}>
                                        <td>{predmet.naziv}</td>
                                        <td><Button onClick={() => prijaviPredmet(predmet)} variant="primary" style={{height: '50px', width : '150px'}}>Prijavi predmet</Button></td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table> : 'Nema predmeta za prijaviti'}
                    </Card.Body>
                </Card>
            </Col>
        </Row>
    )
}

export default PrijavaPredmeta;