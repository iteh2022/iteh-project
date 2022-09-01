import React from 'react';
import '../App.css';
import Button from './Button';
import './HeroSection.css';

function HeroSection() {
  return (
    <div className='hero-container'>
        <h1>Here for all your questions!</h1>
        <p>Have any?</p>
        <div className='hero-btns'>
            <Button className='btns' buttonStyle='btn--outline' buttonSize='btn--large' path='/login'>GET STARTED</Button>
        </div>
    </div>
  )
}

export default HeroSection;