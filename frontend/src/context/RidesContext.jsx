import { createContext, useContext, useState } from "react";
import { AuthContext } from "./AuthContext";
import axios from "axios";


const RidesContext = createContext()

const RidesProvider = ({children}) => {
    const {user} = useContext(AuthContext)
    const [cars, setCars] = useState([])
    const [recentRides, setRecentRides] = useState([]);
    const [myrides, setMyRides] = useState([])
    const [refreshRides, setRefreshRides] = useState(false);
    const [refreshRequests, setRefreshRequests] = useState(false);
    const [rideRequests, setRideRequests] = useState([]);          //driver
    const [myRideRequests, setMyRideRequests] = useState([]);              //passenger

    const changeRefreshRides = (value) => {
      setRefreshRides(value)
    }
    const changeRefreshRequests = (value) => {
      setRefreshRequests(value)
    }

    function toLocalISOString(date) {
      // Ensure the input is a Date object
      
      try{
  
      // Construct the ISO-like string
      return date.format('YYYY-MM-DDTHH:mm:ss');

      }
      catch {
        console.log(date)
        return date
      }
      
  }
  

    const createride = async (data) => {
        
        const req_data = {
            "driver":{"id":user.id},
            "car": {"id":1},
            "startingPoint": data.from,
            "destination": data.to,
            "date": toLocalISOString(data.date),
            "startTime":toLocalISOString(data.departingTime),
            "endTime":toLocalISOString(data.arrivalTime),
            "pricePerHead": parseFloat(data.pricePerHead),
            "availableSeats": parseInt(data.seats),
            "rideStatus": "Active",
            "passengers": []
          }
        
        try {
            // Make a POST request to the /api/create/ endpoint
            const response = await axios.post('/api/rides', req_data, { withCredentials: true });
    
            // Log the response data
            console.log('Ride created successfully:', response.data);
            return true
        } catch (error) {
            // If an error occurs during the request, log it
            console.error('Error creating ride:', error);
            return false
        }
    }

    const editRide = async (ride_id, data) => {
      console.log(ride_id, data)
      
      const req_data = {
        "id": ride_id,
        "driver": { "id": user.id },
        "car": { "id": 1 },
        "startingPoint": data.from,
        "destination": data.to,
        "pricePerHead": parseFloat(data.pricePerHead),
        "availableSeats": parseInt(data.seats),
        "rideStatus": data.status,
        "passengers": data.passengers,
    };

    // Conditionally add date, startTime, and endTime
    
      req_data.date = toLocalISOString(data.date);
      req_data.startTime = toLocalISOString(data.departingTime);
      req_data.endTime = toLocalISOString(data.arrivalTime);
    
      console.log("Edited", req_data)

      try {
          // Make a POST request to the /api/create/ endpoint
          const response = await axios.patch('/api/rides/'+ride_id, req_data, { withCredentials: true });
  
          // Log the response data
          console.log('Ride Updated successfully:', response.data);
          return true
      } catch (error) {
          // If an error occurs during the request, log it
          console.error('Error creating ride:', error);
          return false
      }
  }

  const deleteRide = async (ride_id,data) => {
    const req_data = data;
    req_data.rideStatus = "Deleted"

    console.log(req_data)

    try {
        // Make a POST request to the /api/create/ endpoint
        const response = await axios.patch('/api/rides/'+ride_id, req_data, { withCredentials: true });

        // Log the response data
        console.log('Ride Deleted successfully:', response.data);
        return true
    } catch (error) {
        // If an error occurs during the request, log it
        console.error('Error Deleting ride:', error);
        return false
    }
  }


    const fetchRecentRides = async () => {
        try {
          const response = await axios.get('/api/rides',{withCredentials:true});
          setRecentRides(response.data);
          console.log(recentRides)
        } catch (error) {
          console.error('Error fetching recent rides:', error);
        }
      };

      const createRideRequest = async (data) => {
        const req_data = {
           "ride":data.ride,
           "comments":data.comments,
           "seats_requested":parseInt(data.seats),
           "request_status":"Pending"
        }
        try {
          console.log(req_data)
          const response = await axios.post('/api/rides/ride_requests/create/', req_data,{withCredentials:true});
          console.log('Ride Request Created Successfully')
          return true
        } catch (error) {
          console.error('Error Creating Ride Request:', error);
          return false
        }
      }


      const fetchMyRides = async () => {
        try {
          const response = await axios.get('/api/rides/myrides_list/', {withCredentials:true});
          console.log('Successfully fetched rides offered by me')
          setMyRides(response.data)
          return true
        } catch (error) {
          console.error('Error Creating Ride Request:', error);
          return false
        }
      }

      const fetchRideRequests = async (ride_id) => {
        try {
          const req_data = {
            'ride_id':ride_id
          }
          const response = await axios.post('/api/rides/ride_requests/',req_data, {withCredentials:true});
          console.log(`Successfully fetched requests for the Ride#${ride_id}`)
          setRideRequests(response.data)
          return true
        } catch (error) {
          console.error('Error Creating Ride Request:', error);
          return false
        }
      }

      const handleApproval = async (request_id, accept) => {
        
        try {
          const req_data = {
            req_id: request_id
          }

          if(accept){
            const response = await axios.post('/api/rides/ride_requests/accept/',req_data, {withCredentials:true});
            console.log(`Successfully Accepted Ride Request`)
          }
          if(!accept){
            const response = await axios.post('/api/rides/ride_requests/decline/',req_data, {withCredentials:true});
            console.log(`Successfully Declined Ride Request`)
          }
          return true
        } catch (error) {
          console.error('Error Creating Ride Request:', error);
          return false
        }
      }

      const fetchMyRideRequests = async () => {
        try {
          const response = await axios.get('/api/rides/ride_requests/myrequests/',{withCredentials:true});
          setMyRideRequests(response.data);
        } catch (error) {
          console.error('Error fetching recent rides:', error);
        }
      };

    return(
        <RidesContext.Provider value={{ createride, recentRides, fetchRecentRides, createRideRequest, fetchMyRides, myrides, editRide, deleteRide, refreshRides, refreshRequests, changeRefreshRides, changeRefreshRequests, rideRequests, fetchRideRequests, handleApproval, fetchMyRideRequests, myRideRequests }}>
            {children}
        </RidesContext.Provider>
    )
}

export {RidesContext, RidesProvider};