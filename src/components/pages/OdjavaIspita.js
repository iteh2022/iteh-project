import React, { useContext, useEffect, useState } from 'react'
import './PrijavaIspita.css';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import { Link } from 'react-router-dom'
import { ApiContext, CurrentUserContext } from '../../App'
import axios from "axios"

function OdjavaIspita() {
    const [prijavljeniIspiti, setprijavljeniIspiti] = useState([])
    const { api } = useContext(ApiContext)
    const { currentUser } = useContext(CurrentUserContext)

    function odjaviIspit(prijavljeniIspit) {
        axios.delete(`${api}/prijava-ispita/${prijavljeniIspit.id}`,{
            headers: {
                Authorization: `Bearer ${currentUser}`
            }
        }).then(response => {
            let prijavljeniIspitiG = [...prijavljeniIspiti];
            let index = prijavljeniIspitiG.findIndex((r) => r.id === prijavljeniIspit.id);
            prijavljeniIspitiG.splice(index, 1);
            setprijavljeniIspiti(prijavljeniIspitiG);
        }).catch((error) => {
            console.log(error);
        });
    }

    useEffect(() => {
        axios.get(`${api}/prijava-ispita/student`, {
            headers: {
                'Authorization': `Bearer ${currentUser}`
            }
        }).then(response => {
            setprijavljeniIspiti(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }, [api, currentUser]);
    
    return (
        <Row className='mt-5'>
            <Col sm={12}>
                <Card className='shadow-sm payment-card'>
                    <Card.Body>
                        <h5 className="text-center">Prijavljeni ispiti</h5>
                            {prijavljeniIspiti && prijavljeniIspiti.length > 0 ? <Table striped bordered hover className='text-center'>
                            <thead>
                                <tr className='text-center'>
                                    <th>Ispit iz:</th>
                                </tr>
                            </thead>
                            <tbody>
                                {prijavljeniIspiti.map((prijavljeniIspit, index) => (
                                    <tr key={index}>
                                        <td>{prijavljeniIspit.id}</td>
                                        <td>{prijavljeniIspit.predmet.naziv}</td>
                                        <td><Button onClick={() => odjaviIspit(prijavljeniIspit)} variant="danger" style={{height: '50px', width : '150px'}}>Odjavi</Button></td>
                                    </tr>
                                ))}
                            </tbody>
                        </Table> : 'Nema prijavljenih predmeta'}
                    </Card.Body>
                </Card>
            </Col>
        </Row>
    )
}

export default OdjavaIspita;