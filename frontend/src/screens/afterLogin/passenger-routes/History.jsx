import { useContext, useEffect } from "react";
import PassRequestListView from "../../../components/PassRequestListView";
import { RidesContext } from "../../../context/RidesContext";

export default function PassengerHistory(){

    const {fetchMyRideRequests, myRideRequests} = useContext(RidesContext)
    useEffect(()=>{
        fetchMyRideRequests()
    },[]);
    return(
        
        <>
            <br /><br />
            {myRideRequests.slice().reverse().filter(req => req.requestStatus !== "Pending" ).length!==0 ? myRideRequests.slice().reverse().filter(req => req.requestStatus !== "Pending" ).map((req) => (
                <PassRequestListView key={req.id} data={req}/>
            )) :
            <p>No Requests Found</p>
        }
            
        </>
    )
}