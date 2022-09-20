import React, { useContext, useEffect, useState } from 'react'
import './PrijavaIspita.css';
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Button from 'react-bootstrap/Button'
import Table from 'react-bootstrap/Table'
import { ApiContext, CurrentUserContext } from '../../App'
import axios from "axios"

function PrijavaIspita() {
    const [ispiti, setIspiti] = useState([])
    const { api } = useContext(ApiContext)
    const { currentUser } = useContext(CurrentUserContext)

    function prijaviIspit(ispit) {
        axios.post(`${api}/prijava-ispita`, {predmet: ispit.naziv},
         {
            headers: {
                Authorization: `Bearer ${currentUser}`
            }
        }).then(response => {
            alert("Uspesno prijavljen ispit! :)")
        }).catch((error) => {
            console.log(error);
            alert("Vec ste prijavili ispit iz izabranog predmeta! :)")
        });
    }

    useEffect(() => {
        axios.get(`${api}/prijava-predmeta`, {
            headers: {
                'Authorization': `Bearer ${currentUser}`
            }
        }).then(response => {
            setIspiti(response.data);
        }).catch((error) => {
            console.log(error);
        });
    }, [api, currentUser]);
    
    return (
        <Row className='mt-5'>
        <Col sm={12}>
            <Card className='shadow-sm payment-card'>
                <Card.Body>
                    <h5 className="text-center">Prijavljeni predmeti</h5>
                        {ispiti && ispiti.length > 0 ? <Table striped bordered hover className='text-center'>
                        <thead>
                            <tr className='text-center'>
                                <th>Prijavi ispit iz:</th>
                            </tr>
                        </thead>
                        <tbody>
                            {ispiti.map((ispit, index) => (
                                <tr key={index}>
                                    <td>{ispit.naziv}</td>
                                    <td><Button onClick={() => prijaviIspit(ispit)} variant="primary" style={{height: '50px', width : '150px'}}>Prijavi ispit</Button></td>
                                </tr>
                            ))}
                        </tbody>
                    </Table> : 'Nema prijavljenih predmeta'}
                </Card.Body>
            </Card>
        </Col>
    </Row>

    )
};

export default PrijavaIspita;