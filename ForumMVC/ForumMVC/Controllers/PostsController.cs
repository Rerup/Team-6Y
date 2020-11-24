﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using ForumMVC.Data;
using ForumMVC.Models;

namespace ForumMVC.Controllers
{
    public class PostsController : Controller
    {
        private readonly Services _service;

        public PostsController ( Services service )
        {
            _service = service;
        }

        //GET: Posts
        public async Task<IActionResult> Index (int categoryId)
        {
            var category = await _service.GetCategoryFromIdAsync(categoryId);
            return View(category.Posts);
        }

        //// GET: Posts/Details/5
        //public async Task<IActionResult> Details(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post
        //        .Include(p => p.Category)
        //        .FirstOrDefaultAsync(m => m.ID == id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(post);
        //}

        // GET: Posts/Create
        public IActionResult Create(int categoryID)
        {
            ViewData["CategoryID"] = categoryID;
            return View();
        }

        // POST: Posts/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create ( [Bind("ID,Title,Content,CreatedWhen,Author,UserID,CategoryID")] Post post )
        {
            if (ModelState.IsValid)
            {
                await _service.PostPostAsync(post);
                return RedirectToAction(nameof(Index));
            }
            //ViewData["CategoryID"] = new SelectList(_context.Category, "ID", "ID", post.CategoryID);
            return View(post);
        }

        //// GET: Posts/Edit/5
        //public async Task<IActionResult> Edit(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post.FindAsync(id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }
        //    ViewData["CategoryID"] = new SelectList(_context.Category, "ID", "ID", post.CategoryID);
        //    return View(post);
        //}

        //// POST: Posts/Edit/5
        //// To protect from overposting attacks, enable the specific properties you want to bind to, for 
        //// more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        //[HttpPost]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> Edit(int id, [Bind("ID,Title,Content,CreatedWhen,Author,UserID,CategoryID")] Post post)
        //{
        //    if (id != post.ID)
        //    {
        //        return NotFound();
        //    }

        //    if (ModelState.IsValid)
        //    {
        //        try
        //        {
        //            _context.Update(post);
        //            await _context.SaveChangesAsync();
        //        }
        //        catch (DbUpdateConcurrencyException)
        //        {
        //            if (!PostExists(post.ID))
        //            {
        //                return NotFound();
        //            }
        //            else
        //            {
        //                throw;
        //            }
        //        }
        //        return RedirectToAction(nameof(Index));
        //    }
        //    ViewData["CategoryID"] = new SelectList(_context.Category, "ID", "ID", post.CategoryID);
        //    return View(post);
        //}

        //// GET: Posts/Delete/5
        //public async Task<IActionResult> Delete(int? id)
        //{
        //    if (id == null)
        //    {
        //        return NotFound();
        //    }

        //    var post = await _context.Post
        //        .Include(p => p.Category)
        //        .FirstOrDefaultAsync(m => m.ID == id);
        //    if (post == null)
        //    {
        //        return NotFound();
        //    }

        //    return View(post);
        //}

        //// POST: Posts/Delete/5
        //[HttpPost, ActionName("Delete")]
        //[ValidateAntiForgeryToken]
        //public async Task<IActionResult> DeleteConfirmed(int id)
        //{
        //    var post = await _context.Post.FindAsync(id);
        //    _context.Post.Remove(post);
        //    await _context.SaveChangesAsync();
        //    return RedirectToAction(nameof(Index));
        //}

        //private bool PostExists(int id)
        //{
        //    return _context.Post.Any(e => e.ID == id);
        //}
    }
}
