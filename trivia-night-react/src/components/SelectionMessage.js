
function SelectionMessage({selectedCorrect, selected}) {

    if (selected) {
        if (selectedCorrect) {
            return (
                <>
                    <div className="alert alert-success">That's Right!</div>
                </>
            )
        } else {
            return (
                <>
                    <div className="alert alert-danger">That's not right...</div>
                </>
            )
        }
       
    } else {
        return (
            <div>
            </div>
        )
    }


    
}

export default SelectionMessage;