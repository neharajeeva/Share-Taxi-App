import { useContext, useEffect } from "react";
import { RidesContext } from "../../../context/RidesContext";
import HistoryListView from "../../../components/HistoryListView";

export default function History(){
    const {recentRides, fetchRecentRides} = useContext(RidesContext)

    useEffect(() => {
        fetchRecentRides()
    },[])

    return(
        <>
        <br /><br />
        {recentRides.length !== 0 
        && 
        recentRides.slice().reverse()
        .filter(ride => ride.rideStatus === "Deleted" || ride.rideStatus === "Cancelled" || ride.rideStatus === "Completed")
        .map(ride => (
                <HistoryListView key={ride.id} id={ride.id} data={ride} />
            ))}
        {
            recentRides.length == 0 && <h6>No Requests to show</h6>
        }
        </>
    )
}