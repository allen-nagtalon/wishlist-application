import { useEffect, useState } from 'react'
import { Avatar, Box, Button, Container, IconButton, Menu, MenuItem, Toolbar, Typography } from '@mui/material'
import AppBar from '@mui/material/AppBar'
import ApiInstance from '../../services/ApiInstance'

const pages = ['Home', 'My Wishlists']
const userOptions = ['Log Out']

function NavBar () {
  const [user, setUser] = useState(null)
  const [anchorElUser, setAnchorElUser] = useState(null)

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget)
  }

  const handleCloseUserMenu = (event) => {
    setAnchorElUser(null)
  }

  const logout = () => {
    window.localStorage.removeItem('access_token')
    setUser(null)
  }

  useEffect(() => {
    if (window.localStorage.getItem('access_token')) {
      ApiInstance.get('/user')
        .then((res) => {
          setUser(res.data.data.user)
        })
    }
  }, [])

  return (
    <AppBar id='app-bar' position='fixed' elevation={0} color='light' sx={{ py: 1 }}>
      <Container maxWidth='xl'>
        <Toolbar>
          <Typography
            variant='logo'
            component='a'
            href='#app-bar'
            sx={{
              flexGrow: 1,
              display: { md: 'flex' },
              color: 'text.dark',
              textDecoration: 'none'
            }}
          >
            WISHLIST.IO
          </Typography>

          <Box sx={{ flexGrow: 0, display: { md: 'flex' } }}>
            {pages.map((page) => (
              <Button
                key={page}
                onClick={logout}
                sx={{ color: 'text.dark', display: 'block' }}
              >
                {page}
              </Button>
            ))}
          </Box>

          {user
            ? <>
              <Box sx={{ flexGrow: 0, pl: 5 }}>
                <IconButton onClick={handleOpenUserMenu}>
                  <Avatar src='https://kpopping.com/documents/0f/1/800/240219-XG-Twitter-Update-Jurin-documents-1.jpeg?v=2f72c' />
                </IconButton>
                <Menu
                  sx={{ mt: '45px' }}
                  id='user-menu-appbar'
                  anchorEl={anchorElUser}
                  anchorOrigin={{
                    vertical: 'top',
                    horizontal: 'right'
                  }}
                  keepMounted
                  transformOrigin={{
                    vertical: 'top',
                    horizontal: 'right'
                  }}
                  open={Boolean(anchorElUser)}
                  onClose={handleCloseUserMenu}
                >
                  {userOptions.map((option) => (
                    <MenuItem key={option}>
                      <Typography>{option}</Typography>
                    </MenuItem>
                  ))}
                </Menu>
              </Box>
            </>
            : <>
              <Button component='a' href='/login' sx={{ color: 'text.dark', display: 'block' }}>
                Log In
              </Button>
            </>
          }
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default NavBar
