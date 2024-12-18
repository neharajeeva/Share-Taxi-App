import React, { useContext, useEffect, useState, useRef } from "react";
import { AuthContext } from "../../context/AuthContext";
import Cookies from 'js-cookie'
import {useNavigate} from 'react-router-dom'
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { TimePicker } from '@mui/x-date-pickers/TimePicker';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import SyncAltIcon from '@mui/icons-material/SyncAlt';
import { useLoadScript, Autocomplete as GoogleAddressAutoComplete } from '@react-google-maps/api';


import { TextField, Button, Grid, Typography, IconButton, InputAdornment } from '@mui/material';

import './styles/dashboard.css'
import { Height } from "@mui/icons-material";
import RideListView from "../../components/RideListView";
import CreateRide from "./CreateRide";
import { RidesContext } from "../../context/RidesContext";
import CustomCard from "../../components/CustomCard";

export default function Dashboard() {
    const { user, logout } = useContext(AuthContext);
    const {fetchRecentRides, recentRides} = useContext(RidesContext)

    const fromInputRef = useRef();
    const toInputRef = useRef();

    const [searched, setSearched] = useState(false)
    const [searchResults, setSearchResults] = useState([])
    const [searchData, setSearchData] = useState({
        from:'',
        to:'',
        date:null
    })

    const { isLoaded } = useLoadScript({
        googleMapsApiKey: import.meta.env.VITE_GOOGLE_MAPS_API_KEY, // Using Vite's env variable syntax
        libraries: ['places'],
    });


    const handleChange = (e) => {
        const { name, value } = e.target;
        console.log(name, value)
        setSearchData(prevState => ({
          ...prevState,
          [name]: value
        }));
      };
    
    const handleSubmit = (e) => {
        e.preventDefault()
        console.log(searchData)
        if (recentRides.length !== 0) {
            const filteredRides = recentRides.filter(ride => {
              // Perform your date comparison here, assuming searchData.date is the date you want to compare with
              // Adjust this condition based on how you want to compare the dates
              console.log(ride)
              return ride.date === searchData.date.toISOString().split('T')[0] && ride.rideStatus==='Yet to Start' && ride.driver.username!==user.username;
            });
          
            // Update searchResults with the filtered rides
            setSearchResults([...searchResults, ...filteredRides]);
          }
          setSearched(true)

    }

    const handleLogout = () => {
        // Call the logout function from AuthContext
        logout();
    };

    useEffect(() => {
        fetchRecentRides();
      }, []);

      const handleFromPlaceChanged = () => {
        if (fromInputRef.current) {
            const place = fromInputRef.current.getPlace();
            setSearchData(prevState => ({
                ...prevState,
                from: place.formatted_address || place.name
              }));
        }
    };
    
    const handleToPlaceChanged = () => {
        if (toInputRef.current) {
            const place = toInputRef.current.getPlace();
            setSearchData(prevState => ({
                ...prevState,
                to: place.formatted_address || place.name
              }));
        }
    };

    if (!isLoaded) return <div>Loading...</div>;


    return (
        <>
        <div style={{borderTop:'1px solid white'}} className="dashboard-container wavy-image-back"> {/* Apply inline styles */} 
            <br /><br /><br />
            <h2 style={{fontWeight:'bold', color:'white'}}>Dashboard</h2> 
            <br/>
            <div className="search-box">
                <h3 style={{fontWeight:'bold'}}>Search for a Ride</h3>
                <h5>Where do you wanna go {user!=null && user.username} ? </h5>

                <br/>
                <form className="search-container">
        
                    <div className="item">
                    <GoogleAddressAutoComplete
                                onLoad={(autoC) => fromInputRef.current = autoC}
                                onPlaceChanged={handleFromPlaceChanged}
                            >
                        <TextField id="outlined-basic" label="From" variant="outlined"  fullWidth required/>
                        </GoogleAddressAutoComplete>
                    </div>
                    
                    <SyncAltIcon style={{ margin: '0px 10px' }} />

                    <div className="item">
                    <GoogleAddressAutoComplete
                                onLoad={(autoC) => toInputRef.current = autoC}
                                onPlaceChanged={handleToPlaceChanged}
                            >
                        <TextField id="outlined-basic" label="To" variant="outlined"  fullWidth required/>
                        </GoogleAddressAutoComplete>
                    </div>

                    <div className="item">
                        <DatePicker label='Date of Travel' onChange={(date) => handleChange({target: { name: "date", value: date }})}  fullWidth required/>
                    </div>

                    <div className="item">
                        <button className="wavy-image-back" onClick={handleSubmit} style={{backgroundPosition:'130%'}}>Search Rides</button>
                    </div>
                </form>
            </div>
        </div>

        {searchResults.length ===0 && searched && <h6><br/>No Rides matching your Search</h6>}
        {
            searchResults.length!==0 && (
                <div className="dashboard-container">
                    <h2 style={{fontWeight:'bold'}}>Search Results({searchResults.length})</h2>

                    <br/><br/>
                    {
                        searchResults.map(ride => 
                            <RideListView key={ride.id} data={ride} />
                        )
                    }
                </div>
            )
        }

        
        <div className="dashboard-container inline inline-500">
            <CustomCard 
                imageSrc={'https://www.uber-assets.com/image/upload/f_auto,q_auto:eco,c_fill,w_698,h_698/v1684855112/assets/96/4dd3d1-94e7-481e-b28c-08d59353b9e0/original/earner-illustra.png'}
                title={'Driver Portal'}
                description={'Checkout Driver Portal to create rides and seamlessly track requests'}
                to={'driver'}
                />
            <CustomCard 
                imageSrc={'https://www.uber-assets.com/image/upload/f_auto,q_auto:eco,c_fill,w_698,h_698/v1696243800/assets/62/3b076a-3406-4f3b-89de-2cf1a2ccb907/original/uber-one.jpg'}
                title={'Passenger Portal'}
                description={'Track your requests view your ride history'}
                to={'passenger'}
                />
        </div>
        

        <div className="dashboard-container">
            <h2 style={{fontWeight:'bold'}}>Most Recent Rides</h2>
            {
                
                recentRides
                    .slice()
                    .reverse()
                    .filter(ride => ride.rideStatus !== "Deleted" && ride.driver.username !== user.username)
                    .length !== 0 ? (
                        recentRides
                            .slice()
                            .reverse()
                            .filter(ride => ride.rideStatus !== "Deleted" && ride.driver.username !== user.username)
                            .map(ride => (
                                <RideListView key={ride.id} id={ride.id} data={ride} />
                                //JSON.stringify(ride)
                            ))
                    ) : (
                        <h6>No Requests to show</h6>
                    )
            }

        </div>
        

        <div className="button-absolute-bottom-right">
            <CreateRide />
        </div>
        </>
        
    );
}
