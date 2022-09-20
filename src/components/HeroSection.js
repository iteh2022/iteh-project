import React from 'react';
import '../App.css';
import Button from './Button';
import './HeroSection.css';

function HeroSection() {
  return (
    <div className='hero-container'>
        <h1>DOBRODOŠAO/LA!</h1>
        {/* <p>Have any?</p> */}
        <div className='hero-btns'>
            <Button className='btns' buttonStyle='btn--outline' buttonSize='btn--large' path='/login'>Prijavi se</Button>
        </div>
    </div>
  )
}

export default HeroSection;