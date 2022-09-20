import React, { useContext, useEffect, useState } from 'react'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Card from 'react-bootstrap/Card'
import Modal from 'react-bootstrap/Modal'
import Button from 'react-bootstrap/Button'
import Container from 'react-bootstrap/Container'
import Form from 'react-bootstrap/Form'
import Table from 'react-bootstrap/Table'
import { Link } from 'react-router-dom'
import { ApiContext, CurrentUserContext } from '../App'
import axios from "axios"

function UpisOcene() {
//     const [show, setShow] = useState(false);
//     const [prijavljeniIspiti, setprijavljeniIspiti] = useState([])
//     const { api } = useContext(ApiContext)
//     const { currentUser } = useContext(CurrentUserContext)

//     function prijaviPredmet(predmet) {
//         axios.post(`${api}/prijava-predmeta`, {predmet: predmet.naziv},
//          {
//             headers: {
//                 Authorization: `Bearer ${currentUser}`
//             }
//         }).then(response => {
//             alert("Uspesno prijavljen ispit!")
//         }).catch((error) => {
//             console.log(error);
//             alert("Izabrani predmet je vec prijavljen!")
//         });
//     }
//     const handleClose = () => setShow(false);
//     const handleShow = () => {
//         setShow(true);
//     }

//     function izmeniOcenu() {
//         axios.post(`${api}/reservations`, {
//             headers: {
//                 Authorization: `Bearer ${currentUser.token}`
//             }
//         }).then(response => {
//             let ridesG = [...rides];
//             let rideToEdit = ridesG.find((r => r.id === ride.id));
//             rideToEdit.space -= space;
//             setRides(ridesG);
//             setShow(false);
//         }).catch((error) => {
//             let errorsG = [];
//             console.log(error);
//             if (error.response) {
//                 switch (error.response.status) {
//                     case 400:
//                         errorsG = error.response.data.errors
//                         console.log(errorsG)
//                         setErrors(errorsG);
//                         break;
//                     default:
//                         break;
//                 }
//             }
//         });
//     }

//     useEffect(() => {
//         axios.get(`${api}/prijava-ispita`, {
//             headers: {
//                 'Authorization': `Bearer ${currentUser}`
//             }
//         }).then(response => {
//             setprijavljeniIspiti(response.data);
//         }).catch((error) => {
//             console.log(error);
//             alert("Samo profesori mogu videti sadrzaj ove stranice! :)")
//         });
//     }, [api, currentUser]);
    
//     return (
//         <Container className='mt-5'>
//             <Row>
//                 <h1 className=''>Prijavljeni ispiti<hr /></h1>
//                     {prijavljeniIspiti.map((prijavljeniIspit, index) => (
//                                     <>
//                                     <Col className='mb-4' lg="4">
//                                         <Card>
//                                             <Card.Body>
//                                                 <Card.Title>Ispit</Card.Title>
//                                                 <Card.Text as='div'>
//                                                     <p className='m-0'><span className='fw-bold'></span></p>
//                                                 </Card.Text>
//                                                 <Card.Link onClick={handleShow} className='reservation-link text-decoration-none'>Make a reservation</Card.Link>
//                                             </Card.Body>
//                                         </Card>
//                                     </Col>
//                                     <Modal show={show} onHide={handleClose}>
//                                         <Modal.Header closeButton>
//                                             <Modal.Title>Reservation for 's ride</Modal.Title>
//                                         </Modal.Header>
//                                         <Modal.Body>
//                                             <Form.Group className="mb-3" controlId="formBasicPassword">
//                                                 <Form.Label>Spaces</Form.Label>
//                                                 <Form.Control value={space} onChange={(event) => setSpace(event.target.value)} min="1" max={ride.space} type="number" placeholder="Enter spaces" />
//                                             </Form.Group>
//                                         </Modal.Body>
//                                         <Modal.Footer>
//                                             <Button variant="secondary" onClick={handleClose}>
//                                                 Close
//                                             </Button>
//                                             <Button variant="primary" onClick={makeReservation}>
//                                                 Make a reservation
//                                             </Button>
//                                         </Modal.Footer>
//                                     </Modal>
//                                 </>
//                                 ))}
//             </Row>
//         </Container>
//     )
}

export default UpisOcene;