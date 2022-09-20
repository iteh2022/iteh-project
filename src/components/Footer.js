import React from 'react';
import './Footer.css';
import Button from './Button';

function Footer() {
  function dateToYMD(date) {
    var d = date.getDate();
    var m = date.getMonth() + 1; //Month from 0 to 11
    var y = date.getFullYear();
    return '' + (d <= 9 ? '0' + d : d) + '.' + (m<=9 ? '0' + m : m) + '.' + y + '.';
}
  return (
    <div className='footer-container'>
      <section className='footer-subscription'>
        <p className='footer-subscription-heading'>
          Welcome students, we are here for you!
        </p>
        <p className='footer-subscription-text'>
          Let us know what you need.
        </p>
        <div className='input-areas'>
          <form>
            <input
              className='footer-input'
              name='email'
              type='email'
              placeholder='Your Email'
            />
            <Button path='/login' buttonStyle='btn--primary' onClick={()=>alert("You need to be logged in!")}>Login</Button>
          </form>
          {dateToYMD(new Date())}
        </div>
      </section>
    </div>
  );
}
export default Footer;