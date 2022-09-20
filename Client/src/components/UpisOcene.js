import React, { useContext, useState } from 'react'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Form from 'react-bootstrap/Form'
import { ApiContext, CurrentUserContext } from '../App'
import axios from "axios"

const UpisOcene = ({prijavljeniIspit, prijavljeniIspiti, setprijavljeniIspiti}) =>{
    const [show, setShow] = useState(false);
    const [ocena, setOcena] = useState(5);
    const { api } = useContext(ApiContext)
    const { currentUser } = useContext(CurrentUserContext)
    
    const handleClose = () => setShow(false);
    const handleShow = () => {
        setShow(true);
    }

    function izmeniOcenu() {
        axios.put(`${api}/prijava-ispita/update`,{id: prijavljeniIspit.id, ocena: ocena}, {
            headers: {
                Authorization: `Bearer ${currentUser}`
            }
        }).then(response => {
            let prijavljeniIspitiG = [...prijavljeniIspiti];
            let ispit = prijavljeniIspitiG.find((r => r.id === prijavljeniIspit.id));
            ispit.ocena = ocena;
            setprijavljeniIspiti(prijavljeniIspitiG);
            setShow(false);
        }).catch((error) => {
            console.log(error);
        });
    }
    
    return (
            <>
               <Col className='mb-4' lg="4">
                   <Card>
                                            <Card.Body>
                                                <Card.Title>{prijavljeniIspit.predmet.naziv}</Card.Title>
                                                <Card.Text as='div'>
                                                    <p className='m-0'><span className='fw-bold'>Ime studenta:</span> {prijavljeniIspit.student.ime}</p>
                                                    <p className='m-0'><span className='fw-bold'>Prezime studenta:</span> {prijavljeniIspit.student.prezime}</p>
                                                    <p className='m-0'><span className='fw-bold'>Indeks studenta: </span> {prijavljeniIspit.student.brojIndeksa}</p>
                                                    <p className='m-0'><span className='fw-bold'>Ocena: </span> {prijavljeniIspit.ocena}</p>
                                                </Card.Text>
                                                <Card.Link onClick={handleShow} className='reservation-link text-decoration-none'>Upisite ocenu</Card.Link>
                                            </Card.Body>
                                        </Card>
                                    </Col>
                                    <Modal show={show} onHide={handleClose}>
                                        <Modal.Header closeButton>
                                            <Modal.Title>Upis ocene</Modal.Title>
                                        </Modal.Header>
                                        <Modal.Body>
                                            <Form.Group className="mb-3" controlId="formBasicPassword">
                                                <Form.Label>Ocena</Form.Label>
                                                <Form.Control value={ocena} onChange={(event) => setOcena(event.target.value)} min="5" max="10" type="number" placeholder="Unesite ocenu..." />
                                            </Form.Group>
                                        </Modal.Body>
                                        <Modal.Footer>
                                            <Button variant="secondary" onClick={handleClose}>
                                                Close
                                            </Button>
                                            <Button variant="primary" onClick={izmeniOcenu}>
                                                Upisite ocenu
                                            </Button>
                                        </Modal.Footer>
                                    </Modal>
                                </>
    )
}

export default UpisOcene;