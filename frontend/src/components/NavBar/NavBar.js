import { useState } from 'react'
import { Avatar, Box, Button, Container, IconButton, Menu, MenuItem, Toolbar, Typography } from '@mui/material'
import AppBar from '@mui/material/AppBar'
import ApiInstance from '../../services/ApiInstance'
import { NavLink } from 'react-router-dom'

const pages = [
  {
    title: 'Dashboard',
    to: '/'
  },
  {
    title: 'My Wishlists',
    to: '/wishlists'
  }
]

function NavBar ({ user, setUser }) {
  const [anchorElUser, setAnchorElUser] = useState(null)

  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget)
  }

  const handleCloseUserMenu = (event) => {
    setAnchorElUser(null)
  }

  const logout = () => {
    window.localStorage.removeItem('access_token')
    ApiInstance.defaults.headers.Authorization = null
    setUser(null)
  }

  return (
    <AppBar id='app-bar' position='fixed' elevation={0} color='light' sx={{ py: 1 }}>
      <Container maxWidth='xl'>
        <Toolbar>
          <Typography
            variant='logo'
            component='a'
            href='/'
            sx={{
              flexGrow: 1,
              display: { md: 'flex' },
              color: 'text.dark',
              textDecoration: 'none'
            }}
          >
            WISHLIST.IO
          </Typography>

          {user
            ? <>
              <Box sx={{ flexGrow: 0, display: { md: 'flex' } }}>
                {pages.map((page) => (
                  <Button key={page.title} sx={{ color: 'text.dark', ml: 3 }}>
                    <NavLink to={page.to} style={{ textDecoration: 'none', color: 'inherit' }}>
                      {page.title}
                    </NavLink>
                  </Button>
                ))}
              </Box>
              <Box sx={{ flexGrow: 0, ml: 3 }}>
                <IconButton onClick={handleOpenUserMenu}>
                  <Avatar src={user.imageUrl} />
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
                  <MenuItem onClick={logout}>
                    <Typography>Log Out</Typography>
                  </MenuItem>
                </Menu>
              </Box>
            </>
            : <>
              <Button sx={{ color: 'text.dark', ml: 3 }}>
                <NavLink to='/login' style={{ textDecoration: 'none', color: 'inherit' }}>
                  Log In
                </NavLink>
              </Button>
              <Button sx={{ color: 'text.dark', ml: 3 }}>
                <NavLink to='/register' style={{ textDecoration: 'none', color: 'inherit' }}>
                  Register
                </NavLink>
              </Button>
            </>
          }
        </Toolbar>
      </Container>
    </AppBar>
  )
}

export default NavBar
