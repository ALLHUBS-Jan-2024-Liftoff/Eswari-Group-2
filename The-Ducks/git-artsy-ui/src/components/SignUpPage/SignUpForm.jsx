// TODO Requires handle submit logic
// TODO link when registration approved


const SignUpForm = () => {
    
    return(
        <div>
            <h2>Welcome to gitArtsy!</h2>
            <h3>Create an account:</h3>
                <form action='' method='get'>
                    <div class="form-group">
                        <label for="username">Username:
                        <input type="text" name="username" id="username" required></input>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:
                        <input type="text" name="email" id="email" email required></input>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:
                        <input type="password" name="password" id="password" password required></input>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="verify">Verify Password:
                        <input type="password" name="verify" id="verify" password required>
                        {/* Include verify password logic */}
                        </input>
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="Name">Displayed Name:
                        <input type="text" name="name" id="name"></input>
                        </label>
                    </div>
                    <div class="form-group">
                        {/* Add logic for one type must be selected */}
                        <label for="user-type">Account Type:
                            <label for="artist">  
                            <input type="radio" name="user-type" id="artist"></input>
                        Artist</label>
                            
                            <label for="patron">  
                            <input type="radio" name="user-type" id="patron"></input>
                        Patron</label>
                        </label>
                    </div>
                    <button type='button'>Submit</button>
                </form>
        </div>
    )
}

export default SignUpForm