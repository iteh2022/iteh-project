import React from 'react';
import '../../App.css';
import Button from '../Button';
import'./Login.css';

function Login () {
    return (
    <>
    <div className='login-container'>
        <form>
            <input
              className='footer-input'
              name='email'
              type='email'
              placeholder='Your Email'
            />
            <br />
            <input
              className='footer-input'
              name='password'
              type='password'
              placeholder='Your Password'
            />
            <Button path='/login' buttonStyle='btn--primary' onClick={()=>alert("Wellcome!")}>Login</Button>
          </form>
    </div>
    </>
    )
}

export default Login;
