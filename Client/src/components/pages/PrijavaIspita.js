import React from 'react';
import './PrijavaIspita.css';
import Button from '../Button';
import { ComboBoxComponent } from '@syncfusion/ej2-react-dropdowns';
import PrijavaPredmeta from '../PrijavaPredmeta';

function PrijavaIspita() {
    
    return (
        <>
        <div className='prijavaispita-container'>
            <PrijavaPredmeta />
            <form>
                <ComboBoxComponent placeholder='Choose an exam' 
                dataSource={['Internet tehnologije', 'Elektronsko poslovanje', 'Internet inteligentnih uredjaja', 'Internet marketing', 'Simulacija i simulacioni jezici']}>
                </ComboBoxComponent>
                <div>
                <Button path='/services/prijavaispita' buttonStyle='btn--primary' onClick={()=>alert("Ispit uspesno prijavljen!")}>Prijavi</Button>
                </div>
            </form>
        </div>
        </>

    )
};

export default PrijavaIspita;