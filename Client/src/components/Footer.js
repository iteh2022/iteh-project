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
          Dobro došli studenti, tu smo za vas!
        </p>
        <p className='footer-subscription-text'>
          Kontakt:<br></br>
            <p className='footer-subscription-text'>
              Email: studentskasluzba@gmail.com <br></br>
              Telefon: + 01 234 567 88
            </p>
        </p>
          {dateToYMD(new Date())}
      </section>
    </div>
  );
}
export default Footer;