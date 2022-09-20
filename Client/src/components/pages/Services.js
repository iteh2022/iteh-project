import React from 'react';
import '../../App.css';
import Button from '../Button';
import './Services.css';

function Services () {
    return (
        <>
        <div className='services-container'>
            <h1>SERVICES</h1>
            <div className='services-btns'>
            <Button className='btns' buttonStyle='btn--primary' buttonSize='btn--medium' path='/services/prijavaispita'>Prijava ispita</Button>
            <Button className='btns' buttonStyle='btn--primary' buttonSize='btn--medium' path='/services/odjavaispita'>Odjava ispita</Button>
            <Button className='btns' buttonStyle='btn--primary' buttonSize='btn--medium' path='/services/prijavapredmeta'>Prijava predmeta</Button>
            <Button className='btns' buttonStyle='btn--primary' buttonSize='btn--medium' path='/services/ocene'>Upis ocene</Button>
            </div>
        </div>
        </>
    )
}
export default Services;