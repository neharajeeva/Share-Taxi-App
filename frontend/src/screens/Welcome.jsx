// Welcome.jsx

import React, { useEffect, useRef, useState } from 'react';
import { Link, NavLink } from "react-router-dom";

import './styles/welcome.css'
import NavigationBar from '../components/NaviagationBar';
import { Button } from '../components/CustomButton';
import ParticlesBackground from './Particles';

const Welcome = () => {

    const [stats, setStats] = useState([]);

    // Fetch data from the API
    const fetchStats = async () => {
        try {
            const response = await fetch('/api/stats');  // Adjust the URL if needed
            const data = await response.json();
            setStats(data);  // Store the stats data in state
        } catch (error) {
            console.error('Error fetching stats:', error);
        }
    };

    // Call fetchStats when the component mounts
    useEffect(() => {
        fetchStats();
    }, []);

    return (
            <>
            <header>
            <div className='particles'>
                <ParticlesBackground />
            </div>
            
            <NavigationBar />

            <div className='inline'>
                <div className='grid-item'>
                    <h1>Share Taxi..</h1>
                    <h2>Affordable Rides and make new Friends</h2>

                    <div className='inline-200'>
                        <NavLink to={'login'} >
                            <Button text={'LOGIN'} color={'orange'} />
                        </NavLink>

                        <NavLink to={'signup'}>
                            <Button text={'SIGNUP'} color={'orange-inverse'} />
                        </NavLink>
                    </div>
                </div>

                <div className='grid-item'>
                    <img src="/bluecar.png" alt="" />
                </div>

            </div>
        
        </header>

        <section id={'home'} className='content inline'>
                <div className='grid-item'>
                    <img src="/upper-photo.png" alt="" />
                </div>
                <div className='grid-item'>
                    <h1>
                        Share Taxi
                    </h1>
                    <p className='description'>
                    A transformative solution designed to bridge the transportation gap within university communities.

                    Share Taxi provides a dynamic platform where members with vehicles can seamlessly connect with those in need of transportation.
                    </p>
                </div>

        </section>

        <section id='usage' className='content inline iceblue-back'>
                
                <div className='grid-item'>
                    <h1>
                        Usage
                    </h1>
                    <p className='description'>

                    A user with a car posts details about an upcoming journey, including the starting point, destination, date, fuel price, available seats, car type, and luggage space.

                    Meanwhile, fellow members can effortlessly search for rides matching their destinations, assess the date and pricing compatibility, and simply tag along by sending a request.

                    </p>
                </div>

                <div className='grid-item'>
                    <img src="/lower-photo.png" alt="" />
                </div>

        </section>

        <section id="stats" className="content inline">
            <div className="grid-item">
                <img src="/upper-photo.png" alt="Transport" />
            </div>
            <div className="grid-item">
                <h1>User Statistics</h1>
                <p className="description">
                Explore the transportation trends within our community. These statistics provide insights into the usage patterns of various transport modes and user preferences, helping us enhance the Share Taxi platform.
                </p>
                <div className="stats-table">
                    {stats.length > 0 ? (
                        <table>
                            <thead>
                                <tr>
                                    <th>Transport Mode</th>
                                    <th>Count</th>
                                </tr>
                            </thead>
                            <tbody>
                                {stats.map((stat, index) => (
                                    <tr key={index}>
                                        <td>{stat.transportMode}</td>
                                        <td>{stat.count}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    ) : (
                        <p>Loading stats...</p>
                    )}
                </div>
            </div>
        </section>
        {/* <section id='devteam' className='content orange-back'>
                
            <h1>Meet the Team</h1>
            <img src="/developers.png" style={{position:'relative'}} alt="" />

        </section> */}

        <section className='content dark-blue-back Footer' style={{position:'relative'}}>
                   

            <h1>Contact Us</h1> <br></br>
            <p>If you have any questions, please feel free to reach out to us at:</p>
            <div>Email: <a href="mailto:support@tagalong.com">support@tagalong.com</a></div>
            <div>Phone: <a href="tel:+11234567890">(123) 456-7890</a></div> <br></br>

        </section>
        <footer style={{marginTop:0, paddingTop:'10px', paddingBottom:'10px', }}>
            &copy; 2024 TagAlong. All rights reserved.
            </footer>
        </>
    );
};

export default Welcome;
