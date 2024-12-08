import { useContext, useEffect } from "react";
import ActiveListView from "../../../components/ActiveListView";
import { RidesContext } from "../../../context/RidesContext";
import { AuthContext } from "../../../context/AuthContext";

export default function Active(){
    const {user} = useContext(AuthContext)
    const {recentRides, fetchRecentRides, refreshRides, changeRefreshRides} = useContext(RidesContext)

    useEffect(() => {
        fetchRecentRides()
    },[])

    useEffect(() => {
        if(refreshRides){
            fetchRecentRides()
            changeRefreshRides(false)
        }
        
    },[refreshRides])

    return(
        <div>
            <br />
            <br />
            {console.log(recentRides)}
            {recentRides.length !== 0 && recentRides.slice().reverse().filter(ride => ride.rideStatus !== "Deleted" && ride.driver.username===user.username).map(ride => (
                <ActiveListView key={ride.id} id={ride.id} data={ride} />
            ))}
            {
              recentRides.length == 0 && <h6>No Requests to show</h6>
            }
            
        </div>
    )
}