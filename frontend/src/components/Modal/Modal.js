import { Modal as BaseModal } from '@mui/base/Modal'
import { Box, Button, Fade, Paper, styled, TextField, Typography } from '@mui/material'
import ImageUpload from '../ImageUpload/ImageUpload'
import { useState } from 'react'
import ApiInstance from '../../services/ApiInstance'
import { useParams } from 'react-router-dom'

const WishModalForm = (props) => {
  const { wishlistId } = useParams()

  const [formState, setFormState] = useState({
    title: '',
    description: '',
    url: ''
  })

  const [imageFile, setImageFile] = useState(null)

  const handleInputChange = ({ target }) => {
    setFormState({ ...formState, [target.name]: target.value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()

    ApiInstance.post('/wishes/create', {
      title: formState.title,
      description: formState.description,
      url: formState.url,
      wishlistId: wishlistId
    })
      .then((res) => {
        console.log('Wish creation:', res)
        if (imageFile != null) {
          const formData = new FormData()
          formData.append('wishId', res.data.data.result.wishId)
          formData.append('image', imageFile)

          ApiInstance.put('/image/wish/upload', formData, {
            headers: { 'Content-Type': 'multipart/form-data' }
          })
            .then(() => props.onSubmit())
        }
      })
      .finally(() => {
        setFormState({
          title: '',
          description: '',
          url: ''
        })
        setImageFile(null)
        props.onToggle()
      })
  }

  return (
    <Box component='form' sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <Typography variant='h5' sx={{ pb: 3 }}>
        Add a Wish
      </Typography>
      <Box sx={{ display: 'flex', minWidth: '60dvw' }}>
        <ImageUpload setImageFile={setImageFile} />
        <Box sx={{ alignItems: 'center', display: 'flex', flexGrow: 1, flexDirection: 'column', flex: 2, ml: 3 }}>
          <TextField
            required
            fullWidth
            id='title'
            label='Title'
            name='title'
            value={formState.title}
            onInput={handleInputChange}
            autoFocus
          />
          <TextField
            fullWidth
            id='description'
            label='Description'
            name='description'
            multiline
            row={2}
            value={formState.description}
            onInput={handleInputChange}
            sx={{ my: 2 }}
          />
          <TextField
            fullWidth
            id='url'
            label='URL'
            name='url'
            value={formState.url}
            onInput={handleInputChange}
            sx={{ mb: 2 }}
          />
          <Box sx={{ display: 'flex', justifySelf: 'flex-end' }}>
            <Button
              variant='contained'
              onClick={handleSubmit}
              sx={{
                bgcolor: 'accent.dark',
                borderRadius: 5,
                mr: 2
              }}
            >
              Save
            </Button>
            <Button
              variant='outlined'
              onClick={props.onToggle}
              sx={{
                color: 'accent.dark',
                borderColor: 'accent.dark',
                borderRadius: 5
              }}
            >
              Cancel
            </Button>
          </Box>
        </Box>
      </Box>
    </Box>
  )
}

const WishlistModalForm = (props) => {
  const [formState, setFormState] = useState({
    title: '',
    description: ''
  })

  const handleInputChange = ({ target }) => {
    setFormState({ ...formState, [target.name]: target.value })
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    console.log(ApiInstance.defaults.headers.Authorization)

    ApiInstance.post('/wishlist',
      {
        title: formState.title,
        description: formState.description
      }
    )
      .then(() => {
        setFormState({
          title: '',
          description: ''
        })
        props.onToggle()
        props.onSubmit()
      })
  }

  return (
    <Box component='form' sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center', minWidth: '40dvw' }}>
      <Typography variant='h5'>
        Create a Wishlist
      </Typography>
      <TextField
        required
        fullWidth
        id='title'
        label='Title'
        name='title'
        value={formState.title}
        onInput={handleInputChange}
        autoFocus
        sx={{ my: 3 }}
      />
      <TextField
        fullWidth
        id='description'
        label='Description'
        name='description'
        multiline
        row={2}
        value={formState.description}
        onInput={handleInputChange}
        sx={{ mb: 4 }}
      />
      <Box>
        <Button
          variant='contained'
          onClick={handleSubmit}
          sx={{
            flex: 2,
            bgcolor: 'accent.dark',
            borderRadius: 5,
            mr: 2
          }}
        >
          Save
        </Button>
        <Button
          variant='outlined'
          onClick={props.onToggle}
          sx={{
            flex: 1,
            color: 'accent.dark',
            borderColor: 'accent.dark',
            borderRadius: 5
          }}
        >
          Cancel
        </Button>
      </Box>
    </Box>
  )
}

const CustomModal = (props) => {
  return (
    <Modal
      open={props.modalOpen}
      onClose={props.handleModalToggle}
      closeAfterTransition
    >
      <Fade in={props.modalOpen}>
        <Paper sx={{ p: 5 }}>
          {props.wishModal && <WishModalForm onToggle={props.handleModalToggle} onSubmit={props.onSubmit} />}
          {props.wishlistModal && <WishlistModalForm onToggle={props.handleModalToggle} onSubmit={props.onSubmit} />}
        </Paper>
      </Fade>
    </Modal>
  )
}

const Modal = styled(BaseModal)`
  position: fixed;
  z-index: 1300;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
`

export default CustomModal
