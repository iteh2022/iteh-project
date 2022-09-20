import React, { useContext, useEffect, useState } from 'react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import axios from "axios"
import { ApiContext, CurrentUserContext } from '../App'
import UpisOcene from './UpisOcene'

const PrijavljeniIspiti = () => {
    const [prijavljeniIspiti, setprijavljeniIspiti] = useState([])
    const { api } = useContext(ApiContext);
    const { currentUser } = useContext(CurrentUserContext)

    useEffect(() => {
        axios.get(`${api}/prijava-ispita`, {
            headers: {
                'Authorization': `Bearer ${currentUser}`
            }
        }).then(response => {
            setprijavljeniIspiti(response.data);
        }).catch((error) => {
            console.log(error);
            alert("Samo profesori mogu videti sadrzaj ove stranice! :)")
        });
    }, [api, currentUser]);

    return (
        <Container className='mt-5'>
            <Row>
                <h1 className=''>Prijavljeni ispiti <hr /></h1>
                {prijavljeniIspiti && prijavljeniIspiti.map((prijavljeniIspit, index) => (
                    <UpisOcene key={index} prijavljeniIspit={prijavljeniIspit} prijavljeniIspiti={prijavljeniIspiti} setprijavljeniIspiti={setprijavljeniIspiti} />
                ))}
            </Row>
        </Container>
    )
}

export default PrijavljeniIspiti